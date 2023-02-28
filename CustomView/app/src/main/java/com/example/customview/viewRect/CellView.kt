package com.example.customview.viewRect

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.example.customview.R
import java.lang.Float.min
import kotlin.math.max
import kotlin.properties.Delegates
import kotlin.random.Random

class CellView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int,
) :
    View(context, attrs, defStyleAttr, defStyleRes) {
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context,
        attrs,
        defStyleAttr,
        0)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    var cellColor: Int by Delegates.notNull<Int>()
    private var cellSize: Float = 0f
    private val fieldRect = RectF()
    private var cellPadding: Float = 0f

    private lateinit var cellPaint: Paint

    init {
        if (attrs != null) {
            initColor(attrs, defStyleAttr, defStyleRes)
        } else {
            cellColor = getRndColor()
        }
        initPaint()
    }

    private fun initPaint() {
        cellPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        cellPaint.color = cellColor
        cellPaint.style = Paint.Style.FILL_AND_STROKE
    }

    private fun initColor(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CellView, defStyleAttr, defStyleRes)

        cellColor = typedArray.getColor(R.styleable.CellView_color, getRndColor())

        typedArray.recycle()
    }

    private fun getRndColor(): Int {
        return Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(fieldRect.left, fieldRect.top, fieldRect.right, fieldRect.bottom, cellPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val minHeight = suggestedMinimumHeight + paddingTop + paddingBottom
        val cellRec = kotlin.math.min(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec))

        val desiredCellSizePixels =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, cellRec.toFloat(),
                resources.displayMetrics).toInt()
        val desiredWith =
            max(minWidth, desiredCellSizePixels + paddingRight + paddingLeft)
        val desireHeight = max(minHeight, desiredCellSizePixels + paddingTop + paddingBottom)
        val side = kotlin.math.min(resolveSize(desiredWith, widthMeasureSpec), resolveSize(desireHeight, heightMeasureSpec))
        setMeasuredDimension(
            side,
            side
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateViewSizes()
    }

    private fun updateViewSizes() {
        val safeWidth = width - paddingLeft - paddingRight
        val safeHeight = height - paddingTop - paddingBottom

        cellSize = min(safeWidth.toFloat(), safeHeight.toFloat())
        cellSize = width.toFloat()
        cellPadding = cellSize * 0.2f
        val fieldWith = cellSize
        val fieldHeight = cellSize

        fieldRect.left = paddingLeft + (safeWidth - fieldWith) / 2
        fieldRect.top = paddingTop + (safeHeight - fieldHeight) / 2
        fieldRect.right = fieldRect.left + fieldWith
        fieldRect.bottom = fieldRect.top + fieldHeight
    }

}