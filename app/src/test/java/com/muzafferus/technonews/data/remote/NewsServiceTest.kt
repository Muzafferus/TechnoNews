package com.muzafferus.technonews.data.remote

import com.google.common.truth.Truth.assertThat
import com.muzafferus.technonews.util.Utility
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsServiceTest {

    private lateinit var service: NewsService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }

    private fun enqueueMockResponse() {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("success_response.json")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @Test
    fun getArticleList_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse()
            val responseBody = service.getArticleList(
                Utility.API_KEY,
                Utility.COUNTRY,
                Utility.CATEGORY_TECHNOLOGY
            ).body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?apiKey=cfc21c478f134f899708af141976e2ab&country=us&category=technology")
        }
    }

    @Test
    fun getArticleList_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse()
            val responseBody = service.getArticleList(
                Utility.API_KEY,
                Utility.COUNTRY,
                Utility.CATEGORY_TECHNOLOGY
            ).body()
            val article = responseBody?.articles?.get(0)
            assertThat(article?.author).isEqualTo("Steve Huff")
            assertThat(article?.title).isEqualTo("Genetically Modified Houseplants Are Here - Newser")
            assertThat(article?.url).isEqualTo("https://www.newser.com/story/334877/genetic-tweaks-may-make-plants-better-air-cleaners.html")
            assertThat(article?.urlToImage).isEqualTo("https://img2-azrcdn.newser.com/image/1470735-12-20230507142501.jpeg")
            assertThat(article?.publishedAt).isEqualTo("2023-05-07T18:55:00Z")
            assertThat(article?.content).isEqualTo("A French biotech company wants to clear the air—literally. Neoplants introduced its genetically-engineered pothos plants late last year, touting the plant's ability to metabolize indoor air pollutant… [+1698 chars]")
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}