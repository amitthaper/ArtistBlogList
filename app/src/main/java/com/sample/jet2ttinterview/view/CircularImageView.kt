package com.sample.jet2ttinterview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.ImageView
import android.graphics.RectF



class CircularImageView: ImageView {
    constructor(viewContext: Context) : this(viewContext, null)

    constructor(viewContext: Context, attrs: AttributeSet?) : this(viewContext, attrs, 0)

    constructor(viewContext: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(viewContext, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        val radius = (this.height / 2).toFloat()
        val path = Path()
        val rect = RectF(0f, 0f, this.width.toFloat(), this.height.toFloat())
        path.addRoundRect(rect, radius, radius, Path.Direction.CW)
        canvas?.clipPath(path)
        super.onDraw(canvas)
    }
}