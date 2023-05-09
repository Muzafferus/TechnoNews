package com.muzafferus.technonews.ui.splash

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.muzafferus.technonews.R
import com.muzafferus.technonews.databinding.FragmentSplashBinding
import com.muzafferus.technonews.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executor

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFingerprint()
    }

    private fun initFingerprint() {
        executor = ContextCompat.getMainExecutor(requireContext())
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        requireContext(),
                        "Authentication error: $errString -- $errorCode", Toast.LENGTH_SHORT
                    ).show()

                    if (errorCode !in arrayListOf(
                            ERROR_CODE_SCREEN_CLICK,
                            ERROR_CODE_CANCEL_BUTTON_CLICK
                        )
                    ) {
                        navController.navigate(R.id.action_splashFragment_to_homeFragment)
                    }
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    navController.navigate(R.id.action_splashFragment_to_homeFragment)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    navController.navigate(R.id.action_splashFragment_to_homeFragment)
                }

            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for ${getString(R.string.app_name)}")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Cancel")
            .build()


        binding.tvImg.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }

        binding.tvImg.performClick()
    }

    companion object {
        const val ERROR_CODE_CANCEL_BUTTON_CLICK = 13
        const val ERROR_CODE_SCREEN_CLICK = 10
    }
}