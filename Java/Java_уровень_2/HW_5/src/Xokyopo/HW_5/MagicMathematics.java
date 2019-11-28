package Xokyopo.HW_5;

import java.util.Arrays;

public class MagicMathematics {
    private float[] arr;
    private Timer timer = new Timer();

    public MagicMathematics(float[] _arr) {
        this.arr = _arr;
    }

    //магическая формула по которой проводим вычисления
    private float mathematicsMetod(float _number, int _index) {
        return (float)(_number * Math.sin(0.2f + _index / 5) * Math.cos(0.2f + _index / 5) * Math.cos(0.4f + _index / 2));
    }

    /**
     * applingMathematicsMetodToArra(float[] _arr, int _startIndexForFormula)
     * принимает на вход массив, и стартовый индекс (который соответсвует номеру начала среза в основном массиве)
     * далее проходим цыклом и выполняем расчеты
     * возвращаем в качестве ответа результирующий массив
     */
    private float[] applingMathematicsMetodToArray(float[] _arr, int _startIndexForFormula) {
        float[] arr = _arr.clone();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = this.mathematicsMetod(arr[i], _startIndexForFormula + i);
        }
        return arr;
    }

    //замеряем сколько нужно времени на наши вычисления в однопотоке
    public long getTimeForCalculatingWithoutTread() {
        float[] arrCopy = arr.clone();

        this.timer.timerStart();

        arrCopy = this.applingMathematicsMetodToArray(arrCopy, 0);

        long timerDef = timer.timerStop();

        //TODO распечатаю для сверки массивов
        this.printFirstFiveAndLastFiveNommerOfArray(arrCopy);

        return timerDef;
    }

    //замеряем сколько нужно времени на наши вычисления в многопотоке
    public long getTimeForCalculatingWithTread(int _threadsCount) {
        float[] arrCopy = arr.clone();
        float[][] arrsForTread ; // объявляем массив для хранения частей основного массива
        Thread[] threads = new Thread[_threadsCount]; //объявляем архив потоков
        MyFloatArrayMethods myFloatArrayMethods = new MyFloatArrayMethods(); //метода разбиения и склейки строк вынечены отдельно

        timer.timerStart();

        arrsForTread = myFloatArrayMethods.divideArraysForTreads(arrCopy, _threadsCount); // разбиваем 1 массив на _threadsCount маленьких

        this.creatingTread(threads, arrsForTread);  //запускаем многопоточный расчет
        this.startingTread(threads);
        this.waitEndingTreadWorck(threads);

        arrCopy = myFloatArrayMethods.mergeArraysToArray(arrsForTread); //собираем наш массив обратно

        long timerDef = timer.timerStop();

        //TODO распечатаю для сверки массивов
        this.printFirstFiveAndLastFiveNommerOfArray(arrCopy);

        return timerDef;
    }

    //создаем потоки вычисления
    private void creatingTread(Thread[] _threads, float[][] _arr) {
        int indexForFormula = 0;                   //определяет начало среза с которым будем работать (нельзя вычислять так как если массив не прямоугольной формы, то получаем ошибку в расчетах)

        for (int i = 0; i < _threads.length; i++) {

            int numberOfTreabs_ = i;                 //из за области видимости пришлось добавить
            int indexForFormula_ = indexForFormula;  ////из за области видимости пришлось добавить

            _threads[numberOfTreabs_] = new Thread(new Runnable() {
                @Override
                public void run() {
                    _arr[numberOfTreabs_] = applingMathematicsMetodToArray(_arr[numberOfTreabs_], indexForFormula_);
                }
            });

            indexForFormula +=_arr[i].length;
        }
    }

    //запускаем потоки вычисления
    private void startingTread(Thread[] _threads) {
        for (int i = 0; i < _threads.length; i++) {
            _threads[i].start();
        }
    }

    //ждем конца выполнения потоков
    private boolean waitEndingTreadWorck(Thread[] _threads) {
        for (int i = 0; i < _threads.length; i++) {
            try {
                _threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void printFirstFiveAndLastFiveNommerOfArray(float... arr) {
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr,0,5)) + " .... " + Arrays.toString(Arrays.copyOfRange(arr,arr.length - 5,arr.length)));
    }
}
