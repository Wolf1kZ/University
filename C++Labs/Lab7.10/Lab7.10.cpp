#include <iostream>
#include <iomanip>
#include <ctime>

using namespace std;

float calcAverageMainDiag(int i, int j, int** arr, const int sizeArrows, const int sizeColumns);
float calcAverageSideDiag(int i, int j, int** arr, const int sizeArrows, const int sizeColumns);

int main()
{
    setlocale(LC_ALL, "Russian");
    srand(time(0));
    int n, m;
    float aver1;
    float aver2;
    float comp;
    cout << "Задайте количество строк в матрице A: ";
    cin >> n;
    cout << "Задайте количество столбцов в матрице А: ";
    cin >> m;
    
    int** arrA = new int*[n];                     //Создание двумерного массива (матрица А)
    for (int i = 0; i < n; i++) {
        arrA[i] = new int [m];
    }

    for (int i = 0; i < n; i++) {           //Заполнение матрицы А рандомными числами от 1 до 50
        for (int j = 0; j < m; j++) {
            arrA[i][j] = 1 + rand() % 50; 
        }
    }

    cout << "Матрица A: \n";
    for (int i = 0; i < n; i++) {           //Вывод матрицы А
        for (int j = 0; j < m; j++) {
            cout << setw(4) << arrA[i][j];
        }
        cout << endl;
    }

    float** arrB = new float* [n];                     //Создание двумерного массива ( матрицы Б)
    for (int i = 0; i < n; i++) {
        arrB[i] = new float[m];
    }

    for (int i = 0; i < n; i++) {           //Заполнение матрицы Б
        for (int j = 0; j < m; j++) {
            aver1 = calcAverageMainDiag(i, j, arrA, n, m);     //Подсчёт среднего арифметического главной диагонали
            aver2 = calcAverageSideDiag(i, j, arrA, n, m);     //Подсчёт среднего арифметического побочной диагонали
            comp = aver1 * aver2;                           
            arrB[i][j] = comp;
        }
    }

    cout << "Матрица B: \n";
    for (int i = 0; i < n; i++) {           //Вывод матрицы Б
        for (int j = 0; j < m; j++) {
            cout << setw(10) << arrB[i][j];
        }
        cout << endl;
    }

    for (int i = 0; i < n; ++i) {           //Очищаем выделенную под динамический двумерный массив А память
        delete[] arrA[i];
    }
    delete[] arrA;

    for (int i = 0; i < n; ++i) {           //Очищаем выделенную под динамический двумерный массив Б память
        delete[] arrB[i];
    }
    delete[] arrB;

    system("pause");
    return 0;
}

float calcAverageMainDiag(int i, int j, int **arr, const int sizeArrows, const int sizeColumns) {    //Функция подсчёта среднего арифметического главной диагонали
    float averageMain = 0;
    int arrow = i;
    int column = j;
    int count = 0;
    float sumAbove = 0;
    float sumUnder = 0;
    while(arrow>=0 && column>=0){           //Цикл, который считает сумму элементов выше элемента [i][j]
        sumAbove += arr[arrow][column];
        count++;
        arrow--;
        column--;
    }
    arrow = i + 1;
    column = j + 1;
    while (arrow < sizeArrows && column < sizeColumns) {     //Цикл, который считает сумму элементов ниже элемента [i][j]
        sumUnder += arr[arrow][column];
        count++;
        arrow++;
        column++;
    }
    averageMain = (sumAbove + sumUnder) / count;

    return averageMain;
}

float calcAverageSideDiag(int i, int j, int **arr, const int sizeArrows, const int sizeColumns) {    //Функция подсчёта среднего арифметического побочной диагонали
    float averageSide = 0;
    int arrow = i;
    int column = j;
    int count = 0;
    float sumAbove = 0;
    float sumUnder = 0;
    while (arrow >= 0 && column < sizeColumns) {       //Цикл, который считает сумму элементов выше элемента [i][j]
        sumAbove += arr[arrow][column];
        count++;
        arrow--;
        column++;
    }
    arrow = i + 1;
    column = j - 1;
    while (arrow < sizeArrows && column >= 0) {       //Цикл, который считает сумму элементов ниже элемента [i][j]
        sumUnder += arr[arrow][column];
        count++;
        arrow++;
        column--;
    }
    averageSide = (sumAbove + sumUnder) / count;

    return averageSide;
}
