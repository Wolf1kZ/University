#include <iostream>
#include <fstream>
#include <string>

using namespace std;

const int a = 49;
const int b = 59;
const int m = 256;

char encryption_char(char c);
char decryption_char(char c);
void encryption_decryption_file(string path, int key);
int gcdex(int a, int b, int& x, int& y);
int ReverseElement(int a, int N);

int main() {
	setlocale(LC_ALL, "Russian");

	int key;
	string file_path;

	while (true) {
		cout <<
			"1. Шифрование файла\n" <<
			"2. Расшифрование файла\n" <<
			"3. Выход\n";
		bool good = true;
		do
		{
			cout << "Выберите действие: ";
			cin >> key;
			if (!(good = cin.good()))
				cout << "Ошибка ввода!" << endl;
			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');
		} while (!good);
		cin.clear();
		file_path = "";
		if (key == 1) {
			cout << endl << "Введите полный путь до файла, который нужно зашифровать: ";
			cin >> file_path;
			encryption_decryption_file(file_path, key);
		}
		else if (key == 2) {
			cout << endl << "Введите полный путь до файла, который нужно расшифровать: ";
			cin >> file_path;
			encryption_decryption_file(file_path, key);
		}
		else if (key == 3) {
			break;
		}
		else {
			cout << "Неверный ввод...\n";
		}
		cout << endl << endl;
	}

	return 0;
}

char encryption_char(char c) {
	return ((char)((a * (int)c + b) % m));
}

char decryption_char(char c) {
	return ((char)((ReverseElement(a, m) * ((int)c - b)) % m));
}

void encryption_decryption_file(string path, int key) {
	fstream file(path);
	string text;
	string line;

	if (!file) {
		cout << "Файл не открыт!";
	}
	else {
		while (getline(file, line)) {
			text += line;
			if (!file.eof()) {
				text += '\n';
			}
		}
	}
	for (int i = 0; i < text.length(); i++) {
		if (key == 1) {
			text[i] = encryption_char(text[i]);
		}
		else if (key == 2) {
			text[i] = decryption_char(text[i]);
		}
	}
	file.close();

	file.open(path, ios::out);
	if (!file) {
		cout << "Файл не открыт!";
	}
	else {
		file << text;
	}
	file.close();
}

int gcdex(int a, int b, int& x, int& y) {
	if (b == 0) {
		x = 1;
		y = 0;
		return a;
	}
	int x1, y1;
	int d1 = gcdex(b, a % b, x1, y1);
	x = y1;
	y = x1 - (a / b) * y1;
	return d1;
}

int ReverseElement(int a, int N) {
	int x, y, d;
	d = gcdex(a, N, x, y);
	if (d != 1) {
		return 1;
	}
	else {
		return x;
	}
}
