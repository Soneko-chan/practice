package ci.nsu.mobile.main.ui.colorsearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ci.nsu.mobile.main.R
import ci.nsu.mobile.main.model.ColorModel

class ColorAdapter(
    private var colors: List<ColorModel>
) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewColorPreview: View = itemView.findViewById(R.id.viewColorPreview)
        private val textColorName: TextView = itemView.findViewById(R.id.textColorName)

        fun bind(color: ColorModel) {
            textColorName.text = color.name
            viewColorPreview.setBackgroundColor(
                ContextCompat.getColor(itemView.context, color.colorRes)
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_color, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount() = colors.size

    fun updateColors(newColors: List<ColorModel>) {
        colors = newColors
        notifyDataSetChanged()
    }
}