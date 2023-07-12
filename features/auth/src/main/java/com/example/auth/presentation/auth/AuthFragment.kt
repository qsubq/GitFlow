package com.example.auth.presentation.auth

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.auth.R
import com.example.auth.databinding.FragmentAuthBinding
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignIn.setOnClickListener {
        }
        spannableText()
        eTObservable()
    }

    private fun eTObservable() {
        val loginObservable: Observable<String> = binding.ETEmail.textChanges()
            .map { it.toString() }
            .debounce(500, TimeUnit.MILLISECONDS)

        val passwordObservable: Observable<String> = binding.ETPassword.textChanges()
            .map { it.toString() }
            .debounce(500, TimeUnit.MILLISECONDS)

        val observable = Observable.combineLatest(
            loginObservable,
            passwordObservable,
            BiFunction<String, String, Boolean> { login, password ->
                login.length >= 6 && password.length >= 6
            },
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { canLogin ->
                binding.btnSignIn.isEnabled = canLogin
            }
    }

    private fun spannableText() {
        val forgerPasswordSpanText =
            SpannableStringBuilder(requireContext().getString(R.string.forget_password))
        val clickableForgerPasswordSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {}
        }
        forgerPasswordSpanText.setSpan(
            clickableForgerPasswordSpan,
            0,
            forgerPasswordSpanText.lastIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
        )
        binding.tvForgerPassword.setText(forgerPasswordSpanText, TextView.BufferType.SPANNABLE)

        val registrationSpanText =
            SpannableStringBuilder(requireContext().getString(R.string.forget_password))
        val clickableRegistrationSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {}
        }
        registrationSpanText.setSpan(
            clickableRegistrationSpan,
            0,
            forgerPasswordSpanText.lastIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE,
        )
        binding.tvRegistration.setText(registrationSpanText, TextView.BufferType.SPANNABLE)
    }
}
