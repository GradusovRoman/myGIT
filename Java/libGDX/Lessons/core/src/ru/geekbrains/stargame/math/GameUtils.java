package ru.geekbrains.stargame.math;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import ru.geekbrains.stargame.template.Rect;

import java.util.Random;

public class GameUtils {

    public static Matrix4 calcTransitionMatrix4(Rect src, Rect dest) {
        Matrix4 matrix4 = new Matrix4();
        matrix4.idt()
                .translate(new Vector3(dest.getCenter(), 0f))
                .scale(dest.getHalfWidth()/ src.getHalfWidth(), dest.getHalfHeight()/ src.getHalfHeight(), 1f)
                .translate(-src.getCenter().x, -src.getCenter().y, 0f);
//                .translate(new Vector3(src.getCenter(),0f));
        return matrix4;
    }

    public static Matrix3 calcTransitionMatrix3(Rect src, Rect dest) {
        Matrix3 matrix3 = new Matrix3();
        matrix3.idt() //получили единичную матрицу
                .translate(dest.getCenter()) //(0,0)
                .scale(dest.getHalfWidth()/ src.getHalfWidth(), dest.getHalfHeight()/ src.getHalfHeight()) //смаштабировали размеры матрицы
                .translate(-src.getCenter().x, -src.getCenter().y); //(-300, -400)
        //                .translate(src.getCenter()); // почуму так не работает то???? ппц Ж(
        return matrix3;
    }

    public static float getRandomByRange(float min, float max) {
        Random random = new Random();
        //Так небольшая подстраховка, на всякий случай.
        if (min > max) {
            float a = min;
            min = max;
            max = a;
        }

        return min + random.nextFloat() * (max - min);
    }

    //TODO проверить
    public static TextureRegion[] getTextureRegion(TextureAtlas textureAtlas, String name, int frameCont, int col, int row) {
        TextureRegion textureRegion = textureAtlas.findRegion(name);
        TextureRegion[][] textureRegions = textureRegion.split(textureRegion.getRegionWidth()/col, textureRegion.getRegionHeight()/row);
        TextureRegion[] outTextureRegions = new TextureRegion[frameCont];
        for (int i = 0; i < frameCont; i++) {
            outTextureRegions[i] = textureRegions[i/col][i%col];
        }
        return outTextureRegions;
    }
}
