package com.example.drawingapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs:AttributeSet):View(context,attrs) {
    private var mDrawPath:CustomPath?=null
    private var mCanvasBitmap:Bitmap?=null
    private var mdrawPaint:Paint?=null
    private var mCanvasPaint:Paint?=null
    private var mBrushSize:Float=0.toFloat();
    private var color= Color.BLACK
    private var canvas:Canvas?=null
    private val mPaths=ArrayList<CustomPath>()
    private val mUndoPaths=ArrayList<CustomPath>()

    init {
        setupDrawing()
    }

    fun onCLickUndo(){
        if(mPaths.size>0){
            mUndoPaths.add(mPaths.removeAt(mPaths.size-1))
            //redraw entire page
            invalidate()
        }
    }

    private fun setupDrawing(){
        mdrawPaint=Paint()
        mDrawPath=CustomPath(color,mBrushSize)
        mdrawPaint!!.color=color
        mdrawPaint!!.style=Paint.Style.STROKE
        mdrawPaint!!.strokeJoin=Paint.Join.ROUND
        mdrawPaint!!.strokeCap=Paint.Cap.ROUND
        mCanvasPaint=Paint(Paint.DITHER_FLAG)
       // mBrushSize=20.toFloat()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCanvasBitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
        canvas=Canvas(mCanvasBitmap!!)
    }

    //change canvas to ? if fails
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawBitmap(mCanvasBitmap!!,0f,0f,mCanvasPaint)

        for (path in mPaths){
            mdrawPaint!!.strokeWidth=path.brushThickness
            mdrawPaint!!.color=path.color
            canvas?.drawPath(path,mdrawPaint!!)
        }

        if(!mDrawPath!!.isEmpty){
            mdrawPaint!!.strokeWidth=mDrawPath!!.brushThickness
            mdrawPaint!!.color=mDrawPath!!.color
            canvas?.drawPath(mDrawPath!!,mdrawPaint!!)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touchx=event?.x
        val touchy=event?.y

        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                mDrawPath!!.color=color
                mDrawPath!!.brushThickness=mBrushSize
                mDrawPath!!.reset()
                if(touchx!=null && touchy!=null){
                    mDrawPath!!.moveTo(touchx,touchy)
                }
            }
            MotionEvent.ACTION_MOVE->{
                if(touchx!=null && touchy!=null){
                    mDrawPath!!.lineTo(touchx,touchy)
                }
            }
            MotionEvent.ACTION_UP->{
                mPaths.add(mDrawPath!!)
                mDrawPath=CustomPath(color,mBrushSize)
            }
            else-> return false
        }

        invalidate()

        return true

    }

    fun setSizeForBrush(newSize:Float){
        mBrushSize=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,newSize,resources.displayMetrics);
        mdrawPaint!!.strokeWidth=mBrushSize;
    }

    fun setColor(newColor:String){
        color=Color.parseColor(newColor)
        mdrawPaint!!.color=color
    }

   internal inner class CustomPath(var color:Int,var brushThickness:Float) : Path(){

    }
}

