package com.sdk.mvvmroomdb103

import android.graphics.Color
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sdk.mvvmroomdb103.database.Note
import com.sdk.mvvmroomdb103.databinding.FragmentSecondBinding
import java.util.*

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { (activity as MainActivity).viewModel }
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        note = arguments?.getParcelable("note")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val random = Random()
        val color = Color.argb(
            255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )

        binding.constraintlayout.setBackgroundColor(color)

        if(note != null) {
            binding.apply {
                noteTitle.setText(note?.noteTitle)
                noteContent.setText(note?.noteContent)
            }
        }

        binding.apply {
            floatingActionButton2.setOnClickListener {
                val title = binding.noteTitle.text.toString().trim()
                val content = binding.noteContent.text.toString().trim()
                val time = DateFormat.format("MM, dd, hh:mm:ss", Date())
                if (note == null) {
                    viewModel.saveNote(
                        Note(
                            noteTitle = title,
                            noteContent = content,
                            time = time.toString()
                        )
                    )
                    snackBar("Saved")
                } else {
                    viewModel.updateNote(Note(note?.id!!, title, content, time.toString()))
                    snackBar("Updated")
                }
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}