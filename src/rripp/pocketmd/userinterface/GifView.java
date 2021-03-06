package rripp.pocketmd.userinterface;

import java.io.InputStream;

import rripp.pocketmd.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.view.View;

public class GifView extends View {

	Movie movie, movie1;
	InputStream is = null, is2 = null;
	long moviestart;
	
	public GifView(Context context) {
		super(context);
		is=context.getResources().openRawResource(R.drawable.loader);
		movie=Movie.decodeStream(is);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawColor(Color.WHITE);
		super.onDraw(canvas);
		long now=android.os.SystemClock.uptimeMillis();
		System.out.println("now="+now);
		if (moviestart == 0) { // first time
			moviestart = now;
		}
		System.out.println("\tmoviestart="+moviestart);
		int relTime = (int)((now - moviestart) % movie.duration()) ;
		System.out.println("time="+relTime+"\treltime="+movie.duration());
		movie.setTime(relTime);
		movie.draw(canvas,this.getWidth()/2-20,this.getHeight()/2-40);
		this.invalidate();
		}

}
