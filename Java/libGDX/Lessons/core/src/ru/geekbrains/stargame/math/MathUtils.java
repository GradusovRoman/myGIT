package ru.geekbrains.stargame.math;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

import ru.geekbrains.stargame.template.Rect;

public class MathUtils {

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

}
