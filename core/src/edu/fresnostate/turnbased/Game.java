package edu.fresnostate.turnbased;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;


public class Game extends ApplicationAdapter
{
	SpriteBatch					batch;
	// Texture img;
	TiledMap					map;
	OrthogonalTiledMapRenderer	renderer;
	OrthographicCamera			cam;
	private int					oldX;
	private int					oldY;

	@Override
	public void create ()
	{
		batch = new SpriteBatch ();
		// img = new Texture("badlogic.jpg");
		map = new TmxMapLoader ().load ("test.tmx");
		renderer = new OrthogonalTiledMapRenderer (map);
		cam = new OrthographicCamera (800, 800);
		cam.translate (new Vector2 (320, 240));
		cam.update ();
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor (0, 0, 0, 1);
		Gdx.gl.glClear (GL20.GL_COLOR_BUFFER_BIT);
		renderer.setView (cam);
		renderer.render ();
		// batch.begin();
		// batch.draw(img, 0, 0);
		// batch.end();
		if (Gdx.input.isKeyPressed (Input.Keys.ESCAPE))
		{
			Gdx.app.exit ();
		}
		if (Gdx.input.justTouched ())
		{
			oldX = Gdx.input.getX ();
			oldY = Gdx.input.getY ();
		}
		if (Gdx.input.isTouched ())
		{
			int x = Gdx.input.getX ();
			int y = Gdx.input.getY ();
			cam.translate (oldX - x, y - oldY);
			cam.update ();
			oldX = x;
			oldY = y;
		}
	}
}
