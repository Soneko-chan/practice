package ci.nsu.mobile.main.ui.colorsearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import ci.nsu.mobile.main.R
import ci.nsu.mobile.main.model.ColorModel

class ColorSearchFragment : Fragment() {

    private val viewModel: ColorSearchViewModel by viewModels()

    private lateinit var editTextColor: EditText
    private lateinit var buttonSearch: Button
    private lateinit var recyclerViewColors: RecyclerView
    private lateinit var colorAdapter: ColorAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_color_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    private fun initViews(view: View) {
        editTextColor = view.findViewById(R.id.editTextColor)
        buttonSearch = view.findViewById(R.id.buttonSearch)
        recyclerViewColors = view.findViewById(R.id.recyclerViewColors)
    }

    private fun setupRecyclerView() {
        colorAdapter = ColorAdapter(emptyList())
        recyclerViewColors.adapter = colorAdapter
    }

    private fun setupObservers() {
        viewModel.searchResult.observe(viewLifecycleOwner) { color ->
            updateButtonColor(color)
        }

        viewModel.allColors.observe(viewLifecycleOwner) { colors ->
            colorAdapter.updateColors(colors)
        }
    }

    private fun setupClickListeners() {
        buttonSearch.setOnClickListener {
            val query = editTextColor.text.toString().trim()
            if (query.isNotEmpty()) {
                viewModel.searchColor(query)
            }
        }
    }

    private fun updateButtonColor(color: ColorModel?) {
        if (color != null) {
            val colorInt = ContextCompat.getColor(requireContext(), color.colorRes)
            buttonSearch.setBackgroundColor(colorInt)
        } else {
            val defaultColor = ContextCompat.getColor(requireContext(), R.color.green)
            buttonSearch.setBackgroundColor(defaultColor)
        }
    }
}