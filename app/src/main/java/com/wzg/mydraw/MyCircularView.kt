package com.wzg.mydraw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * You might think you can read it or optimize it, but no,you're wrong, Please  believe me. You'll see to do.
 * author:wzg
 * createTime: 2022/2/16/016 14:58
 * desc: 绘制一个圆
 */
class MyCircularView  : View {


    private val TAG = "MyCirView"


    private var mPaint: Paint? = null
    private var size = -1


    constructor(context: Context?) : this(context, null) {
        Log.e(TAG, "执行了 constructor(2) ")
    }

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {
        Log.e(TAG, "执行了 constructor(3) ")
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr,
        0
    ) {
        Log.e(TAG, "执行了 constructor(4) ")
        initPaint()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = measuredWidth
        val height = measuredHeight
        size = Math.min(width, height)

        //设置外观尺寸
        setMeasuredDimension(size * 2, size * 2)
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e(TAG, "执行了 onDraw(canvas: Canvas?)  " + (canvas == null) + "\t" + (mPaint == null))
        canvas?.apply {
            drawCircle(size.toFloat(), size.toFloat(), size.toFloat(), mPaint!!)
        }
    }

    private fun initPaint() {
        mPaint = Paint()
        mPaint?.apply {
            color = Color.RED//画笔颜色
            isAntiAlias = true//抗锯齿
            style = Paint.Style.FILL
        }
    }


}