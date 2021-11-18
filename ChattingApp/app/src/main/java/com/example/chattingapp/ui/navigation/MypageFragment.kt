package com.example.chattingapp.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.chattingapp.R

class MypageFragment : Fragment() {

    companion object {
        fun newInstance() : MypageFragment {
            return MypageFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_my_page, container, false)

        return view
    }
}