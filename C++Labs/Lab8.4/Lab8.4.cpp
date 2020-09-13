#include <iostream>
#include <fstream>

using namespace std;

int main(){
    setlocale(LC_ALL, "rus");

    fstream file1;
    fstream file2;
    fstream tempFile;
    const static int BUF_SIZE = 4096;
    char buf[BUF_SIZE];

    file1.open("C:\\Folder\\f1.txt", ios_base::in);     //Открываем файл ф1 для чтения
    file2.open("C:\\Folder\\f2.txt", ios_base::in);     //Открываем файл ф2 для чтения
    tempFile.open("C:\\Folder\\h.txt", ios_base::out, ios_base::trunc);     //Открываем файл ш для записи с полной очисткой его содержимого

    if (!file1.is_open() || !file2.is_open() || !tempFile.is_open()) {      //Проверяем, открылись ли файлы. Если нет какого-либо файла, 
        cout << "Один из файлов не найден.\n";                              //программа заканчивает выполнение.
        return 1;
    }
    else {               
        do {
            file1.read(&buf[0], BUF_SIZE);                  //Читаем из файла 1 максимум 4096(так как это ограничение нашего буфера)
            tempFile.write(&buf[0], file1.gcount());        //Записываем в временный файл(ш) то, что считали
        } while (file1.gcount() > 0);
        file1.close();                                      //Закрываем файлы ф1 и ш, чтобы открыть их с другими опциями
        tempFile.close();
       
        file1.open("C:\\Folder\\f1.txt", ios_base::out, ios_base::trunc);   //Открываем файл ф1 для записи с полной очисткой его содержимого
        for (int i = 0; i < BUF_SIZE; i++) {                                //Очищаем буфер
            buf[i] = 0;
        }        
        do {
            file2.read(&buf[0], BUF_SIZE);                                  //Читаем из файла 2 максимум 4096 символов в массив символов (buf)
            file1.write(&buf[0], file2.gcount());                           //Записываем в файл(ф1) то, что считали
        } while (file2.gcount() > 0);
        file2.close();                                                      //Закрываем файлы ф1 и ф2, чтобы открыть их с другими опциями
        file1.close();

        tempFile.open("C:\\Folder\\h.txt", ios_base::in);                   //Открываем файл ш для чтения
        file2.open("C:\\Folder\\f2.txt", ios_base::out, ios_base::trunc);   //Открываем файл ф2 для записи с полной очисткой его содержимого
        for (int i = 0; i < BUF_SIZE; i++) {                                //Очищаем буфер
            buf[i] = 0;
        }
        do {
            tempFile.read(&buf[0], BUF_SIZE);                               //Читаем из файла ш максимум 4096 символов в массив символов (buf)
            file2.write(&buf[0], tempFile.gcount());                        //Записываем в файл(ф2) то, что считали
        } while (tempFile.gcount() > 0);
        tempFile.close();                                                   //Закрываем файлы ш и ф2, чтобы открыть их с другими опциями
        file2.close();

        tempFile.open("C:\\Folder\\h.txt", ios_base::out, ios_base::trunc); //Открываем файл ш с опцией очистки
        tempFile.close();                                                   //Закрываем файл ш
    }

    return 0;
}

