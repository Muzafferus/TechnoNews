package com.muzafferus.technonews.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.muzafferus.technonews.data.entities.Article
import kotlinx.coroutines.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
@SmallTest
class ArticleDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var articleDao: ArticleDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        articleDao = database.articleDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertArticle_returnsTrue() = runBlocking {
        val article = Article(
            id = 1,
            author = "Elahe Izadi",
            title = "Washington Post wins three Pulitzer Prizes, including for abortion coverage, feature writing - The Washington Post",
            description = "Reporters Caroline Kitchener and Eli Saslow were honored with journalism Pulitzers, and “His Name is George Floyd” received a nonfiction Pulitzer.",
            url = "https://www.washingtonpost.com/media/2023/05/08/washington-post-pulitzer-prize-2023/",
            urlToImage = "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/ARPSSCRBYQI6NOKEKL33C6J5VY.jpg&w=1440",
            publishedAt = "2023-05-08T20:13:35Z",
            content = "Comment on this story\r\nComment\r\nThe Washington Post won three Pulitzer Prizes on Monday, including one for reporting on the consequences of changing abortion laws, and another for a series of intimat… [+8911 chars]"
        )

        articleDao.insert(article)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            articleDao.getArticles().collect {
                assertThat(it).contains(article)
                latch.countDown()
            }
        }

        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun getOneArticle_returnsTrue() = runBlocking {
        val article = Article(
            id = 1,
            author = "Elahe Izadi",
            title = "Washington Post wins three Pulitzer Prizes, including for abortion coverage, feature writing - The Washington Post",
            description = "Reporters Caroline Kitchener and Eli Saslow were honored with journalism Pulitzers, and “His Name is George Floyd” received a nonfiction Pulitzer.",
            url = "https://www.washingtonpost.com/media/2023/05/08/washington-post-pulitzer-prize-2023/",
            urlToImage = "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/ARPSSCRBYQI6NOKEKL33C6J5VY.jpg&w=1440",
            publishedAt = "2023-05-08T20:13:35Z",
            content = "Comment on this story\r\nComment\r\nThe Washington Post won three Pulitzer Prizes on Monday, including one for reporting on the consequences of changing abortion laws, and another for a series of intimat… [+8911 chars]"
        )

        articleDao.insert(article)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            articleDao.getArticle(article.id).collect {
                assertThat(it).isEqualTo(article)
                latch.countDown()
            }
        }

        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun delete_returnsTrue() = runBlocking {
        val article = Article(
            id = 1,
            author = "Elahe Izadi",
            title = "Washington Post wins three Pulitzer Prizes, including for abortion coverage, feature writing - The Washington Post",
            description = "Reporters Caroline Kitchener and Eli Saslow were honored with journalism Pulitzers, and “His Name is George Floyd” received a nonfiction Pulitzer.",
            url = "https://www.washingtonpost.com/media/2023/05/08/washington-post-pulitzer-prize-2023/",
            urlToImage = "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/ARPSSCRBYQI6NOKEKL33C6J5VY.jpg&w=1440",
            publishedAt = "2023-05-08T20:13:35Z",
            content = "Comment on this story\r\nComment\r\nThe Washington Post won three Pulitzer Prizes on Monday, including one for reporting on the consequences of changing abortion laws, and another for a series of intimat… [+8911 chars]"
        )
        val articleSecond = Article(
            id = 2,
            author = null,
            title = "US government, Texas surge personnel to Mexico border as Title 42 end looms - Reuters",
            description = "The Biden administration and Texas state government are surging personnel to the U.S.-Mexico border to prepare for a possible increase in illegal immigration when COVID-19 restrictions known as Title 42 are set to end on Thursday.",
            url = "https://www.reuters.com/world/us/texas-deploys-special-border-force-drive-back-expected-migrant-surge-2023-05-08",
            urlToImage = "https://www.reuters.com/resizer/R-1KRn3M7mxlhg-Gu0WV6YqmtJA=/1200x628/smart/filters:quality(80)/cloudfront-us-east-2.images.arcpublishing.com/reuters/NAQGMCNYGRJIPM3FWCXSGPICEE.jpg",
            publishedAt = "2023-05-08T20:06:00Z",
            content = "WASHINGTON, May 8 (Reuters) - The Biden administration and Texas state government are surging personnel to the U.S.-Mexico border to prepare for a possible increase in illegal immigration when COVID-… [+3088 chars]"
        )

        articleDao.insert(article)
        articleDao.insert(articleSecond)

        articleDao.deleteAll()

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            articleDao.getArticles().collect {
                assertThat(it).doesNotContain(articleDao)
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()

    }
}