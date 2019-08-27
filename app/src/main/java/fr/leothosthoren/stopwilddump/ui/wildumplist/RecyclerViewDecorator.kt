package fr.leothosthoren.stopwilddump.ui.wildumplist

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import fr.leothosthoren.stopwilddump.R

class RecyclerViewDecorator(val adapter: WildDumpAdapter) : RecyclerView.ItemDecoration() {
    companion object {
        private var header: Bitmap? = null
        private val paint = Paint()
        private const val OFFSET = 70
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        initHeader(parent)
        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            val dividerLeft = parent.paddingLeft + child.left
            val dividerTop = child.top
            if (childCount % 2 == 0) {
                header?.let {
                    c.drawBitmap(it, dividerLeft.toFloat(), dividerTop.toFloat() - OFFSET, paint)
                }
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        initHeader(parent)
        val childCount = parent.childCount
        if (childCount % 2 == 0)
            outRect.top = OFFSET + view.paddingTop
    }

    private fun initHeader(parent: RecyclerView) {
        if (header == null) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.header_decoration, null)
            val bitmap = Bitmap.createBitmap(parent.width, OFFSET, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.AT_MOST)
            val heightSpec = View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.AT_MOST)
            view.measure(widthSpec, heightSpec)
            view.layout(0, 0, parent.width, parent.height)
            view.draw(canvas)
            header = bitmap
        }
    }
}