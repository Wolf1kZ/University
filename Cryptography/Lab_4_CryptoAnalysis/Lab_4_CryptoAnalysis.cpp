#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int gcdex(int a, int b, int& x, int& y);
int ReverseElement(int a, int N);
char decryption_char(int a, int b, int m, char c);

int main()
{
	setlocale(LC_ALL, "Russian");

	const int size = 32;
	const char rus_alphabet[size] = { 
		'а','б','в','г','д','е','ж','з','и','й','к','л','м','н',
		'о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ь','ы', 'ъ','э','ю','я' 
	};

	string text = "мэиэщэоэиэчцэюиацшмыдыоэиюыцчлыуиюйбэфоэлэгэвлэчущятлючудэбэфрэиавэъуиытбэйэътщлыцш";
	int freq[size] = { 0 };

	cout << text << endl << endl;
	
	for (int i = 0; i < 32; i++) {
		for (int j = 0; j < (int)text.length(); j++) {
			if (rus_alphabet[i] == text[j]) {
				freq[i]++;
			}
		}
	}

	for (int i = 0; i < 32; i++) {
		cout << rus_alphabet[i] << " in the text " << freq[i] << " times" << endl;
	}

	int maxA = 0, maxB = 0;
	int numA = 0, numB = 0;

	for (int i = 0; i < 32; i++) {
		if (freq[i] > maxA) {
			numA = i;
			maxA = freq[i];
		}
	}
	for (int i = 0; i < 32; i++) {
		if (freq[i] > maxB && i != numA) {
			numB = i;
			maxB = freq[i];
		}
	}

	cout << endl << "Most frequently repeated letters: " << rus_alphabet[numA] << " and " << rus_alphabet[numB] << endl;
	cout << "" << numA << ", " << numB << endl << endl;

	int firstKeyA = 0, firstKeyB = 0;
	cout << "First variant of key" << endl;
	for (int a = 0; a < 32; a++) {
		for (int b = 0; b < 32; b++) {
			if (((14 * a + b) % 32 == numA) && ((11 * a + b) % 32 == numB)) {
				firstKeyA = a;
				firstKeyB = b;
				cout << "a: " << firstKeyA << endl << "b: " << firstKeyB;
			}
		}
	}
	
	int secondKeyA = 0, secondKeyB = 0;
	cout << endl << "Second variant of key" << endl;
	for (int a = 0; a < 32; a++) {
		for (int b = 0; b < 32; b++) {
			if (((14 * a + b) % 32 == numB) && ((11 * a + b) % 32 == numA)) {
				secondKeyA = a;
				secondKeyB = b;
				cout << "a: " << secondKeyA << endl << "b: " << secondKeyB << endl;
			}
		}
	}

	string dec_text1 = "";
	string dec_text2 = "";
	for (int i = 0; i < text.length(); i++) {
		dec_text1 += decryption_char(firstKeyA, firstKeyB, size, text[i]);
		dec_text2 += decryption_char(secondKeyA, secondKeyB, size, text[i]);
		if (dec_text1[i] == 0) {
			dec_text1[i] = 'а';
		}
		if (dec_text2[i] == 0) {
			dec_text2[i] = 'а';
		}
	}
	cout << endl << endl << dec_text1;
	cout << endl << endl << dec_text2 << endl;

    return 0;
}

char decryption_char(int a, int b, int m, char c) {
	return ((ReverseElement(a, m) * (c - b)) % m);
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
		return (x % N + N) % N;
	}
}

