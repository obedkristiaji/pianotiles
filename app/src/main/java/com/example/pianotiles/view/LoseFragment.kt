package com.example.pianotiles.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.pianotiles.databinding.FragmentLoseBinding
import com.example.pianotiles.presenter.IMainPresenter

class LoseFragment : DialogFragment() {
    private lateinit var binding: FragmentLoseBinding
    private lateinit var listener: IMainActivity
    private lateinit var presenter: IMainPresenter

    init {

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentLoseBinding.inflate(inflater, container, false)

        this.init()

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        this.binding.btnReplay.setOnClickListener {
            dismiss()
            this.listener.changePage(2)
        }

        this.binding.btnHome.setOnClickListener {
            dismiss()
            this.listener.changePage(1)
            if(this.presenter.isStartHome() == true) {
                this.listener.updateHighScore(this.presenter.getHS())
            }
        }

        return this.binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is IMainActivity) {
            this.listener = context as IMainActivity
        } else {
            throw ClassCastException(context.toString()
                    + " must implement FragmentListener")
        }
    }

    companion object {
        fun newInstance(presenter: IMainPresenter): LoseFragment {
            val fragment: LoseFragment = LoseFragment()
            fragment.presenter = presenter
            return fragment
        }
    }

    fun setScore(score: Int) {
        this.binding.tvScore.setText("Your Score: " + score.toString())
        this.binding.tvHS.setText("High Score: " + this.presenter.getHS().toString())
    }

    private fun init() {
        this.setScore(this.presenter.getLastScore())
        this.presenter.setStartLose(true)
    }
}