package com.example.parallax;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParallaxExample extends ApplicationAdapter {
	SpriteBatch batch;
	Camera camera;
	Texture img;

	ParallaxLayer[] layers;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		// Viewport size the same as the background texture
		camera = new OrthographicCamera(1920, 1080);

		// Art assets from
		// https://opengameart.org/content/parallax-2d-backgrounds
		layers = new ParallaxLayer[7];
		layers[0] = new ParallaxLayer(new Texture("0.png"), 0.1f, true, false);
		layers[1] = new ParallaxLayer(new Texture("1.png"), 0.2f, true, false);
		layers[2] = new ParallaxLayer(new Texture("2.png"), 0.3f, true, false);
		layers[3] = new ParallaxLayer(new Texture("3.png"), 0.5f, true, false);
		layers[4] = new ParallaxLayer(new Texture("4.png"), 0.8f, true, false);
		layers[5] = new ParallaxLayer(new Texture("5.png"), 1.0f, true, false);
		layers[6] = new ParallaxLayer(new Texture("6.png"), 1.2f, true, false);

		// Could be part of the constructor but this is a bit more flexible (can create the parallax layers before
		// initialising the camera if needed)
		for (ParallaxLayer layer : layers) {
			layer.setCamera(camera);
		}
	}


	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		int speed = 100;
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) camera.position.x -= speed * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) camera.position.x += speed * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) camera.position.y -= speed * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) camera.position.y += speed * Gdx.graphics.getDeltaTime();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (ParallaxLayer layer : layers) {
			layer.render(batch);
		}
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
