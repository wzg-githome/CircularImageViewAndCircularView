package com.wzg.mydraw

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView

/**
 * You might think you can read it or optimize it, but no,you're wrong, Please  believe me. You'll see to do.
 * author:wzg
 * createTime: 2022/2/16/016 15:41
 * desc: 圆角imageView
 */
class MyCircularImageView : AppCompatImageView {


    private val TAG = "MyCircularImageView"

    private var defaultRadius = 0
    private var radius = 0
    private var leftTopRadius = 0
    private var rightTopRadius = 0
    private var leftBottomRadius = 0
    private var rightBottomRadius = 0


    private var widthX = -1
    private var heightX = -1


    constructor(context: Context) : this(context, null) {}
    constructor(context: Context, attrs: AttributeSet?) : this(
        context, attrs, 0
    ) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {

        val mTypeArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.MyCircularImageView)

        mTypeArray.apply {
            radius = getDimensionPixelOffset(
                R.styleable.MyCircularImageView_radius,
                defaultRadius
            )
            leftTopRadius = getDimensionPixelOffset(
                R.styleable.MyCircularImageView_left_top_radius,
                defaultRadius
            )
            leftBottomRadius = getDimensionPixelOffset(
                R.styleable.MyCircularImageView_left_bottom_radius,
                defaultRadius
            )
            rightTopRadius = getDimensionPixelOffset(
                R.styleable.MyCircularImageView_right_top_radius,
                defaultRadius
            )
            rightBottomRadius = getDimensionPixelOffset(
                R.styleable.MyCircularImageView_right_bottom_radius,
                defaultRadius
            )


            if (leftTopRadius == defaultRadius) leftTopRadius = radius
            if (leftBottomRadius == defaultRadius) leftBottomRadius = radius
            if (rightTopRadius == defaultRadius) rightTopRadius = radius
            if (rightBottomRadius == defaultRadius) rightBottomRadius = radius

            mTypeArray.recycle()
        }


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        widthX = width
        heightX = height
    }


    override fun onDraw(canvas: Canvas?) {


        val maxLeft = leftTopRadius.coerceAtLeast(leftBottomRadius)
        val maxRight = rightTopRadius.coerceAtLeast(rightBottomRadius)

        val minWidth = maxLeft + maxRight

        val maxTop = leftTopRadius.coerceAtLeast(rightTopRadius)

        val maxBottom = leftBottomRadius.coerceAtLeast(rightBottomRadius)

        val minHeight = maxTop + maxBottom




            if (widthX >= minWidth && heightX > minHeight) {

                Log.e(TAG, "radius22")

                //四个角
                val path = Path()

                path.moveTo(leftTopRadius.toFloat(), 0f)

                path.lineTo(widthX.toFloat() - leftTopRadius.toFloat(), 0f)
                path.quadTo(widthX.toFloat(), 0f, widthX.toFloat(), rightTopRadius.toFloat())


                path.lineTo(widthX.toFloat(), heightX.toFloat() - rightBottomRadius.toFloat())
                path.quadTo(
                    widthX.toFloat(),
                    heightX.toFloat(),
                    widthX.toFloat() - rightBottomRadius.toFloat(),
                    heightX.toFloat()
                )

                path.lineTo(leftBottomRadius.toFloat(), heightX.toFloat())
                path.quadTo(
                    0f,
                    heightX.toFloat(),
                    0f,
                    heightX.toFloat() - leftBottomRadius.toFloat()
                )


                path.lineTo(0f, leftTopRadius.toFloat())
                path.quadTo(0f, 0f, leftTopRadius.toFloat(), 0f)

                canvas?.clipPath(path)
            }
        super.onDraw(canvas)

    }


}