//Variant - 23
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

const int lengthKey = 6;
const int key[lengthKey] = { 6, 4, 5, 1, 2, 3 };

string encryption_text(string t);
string decryption_text(string t);
void encryption_decryption_file(string path, int key);
int charToInt(char c);

int main()
{
	setlocale(LC_ALL, "Russian");

	int key;
	string file_path;

	while (true) {
		cout <<
			"1. Encryption file\n" <<
			"2. Decryption file\n" <<
			"3. Exit\n";
		bool good = true;
		do
		{
			cout << "Select the action: ";
			cin >> key;
			if (!(good = cin.good()))
				cout << "Error!" << endl;
			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');
		} while (!good);
		cin.clear();
		file_path = "";
		if (key == 1) {
			cout << endl << "Enter file path: ";
			cin >> file_path;
			encryption_decryption_file(file_path, key);
		}
		else if (key == 2) {
			cout << endl << "Enter file path: ";
			cin >> file_path;
			encryption_decryption_file(file_path, key);
		}
		else if (key == 3) {
			break;
		}
		else {
			cout << "Error...\n";
		}
		cout << endl << endl;
	}

	return 0;
}

string encryption_text(string t) {
	string enc_text;
	int added_char;

	if ((int)t.length() % lengthKey != 0) {					//Если кол-во символов в файле не кратно 6, то буду добавлять символы, 
		added_char = 6 - (int)t.length() % lengthKey;		//пока не будет кратности.
	}
	else {
		added_char = 0;
	}
	
	for (int i = 0; i < added_char; i++) {					//Добавляем символы
		t += t[i];
	}
	for (int i = 0; i < (int)t.length(); i += lengthKey)	//Шифрование
	{
		char tmp[lengthKey] = {};
		for (int j = 0; j < lengthKey; j++) {
			tmp[key[j] - 1] = t[i + j];
		}
		for (int j = 0; j < lengthKey; j++) {
			enc_text += tmp[j];
		}
	}

	enc_text += to_string(added_char);						//Дописываю в конец зашифрованного текста кол-во добавленных символов

	return enc_text;
}

string decryption_text(string t) {
	string dec_text;

	int added_char = charToInt(t[t.length() - 1]);					//Последний символ является кол-вом добавленных символов
	t.erase(t.length() - 1);										//Удаляю его
	
	for (int i = 0; i < (int)t.length(); i += lengthKey) {			//Расшифрование
		char tmp[lengthKey] = {};
		for (int j = 0; j < lengthKey; j++) {
			tmp[j] = t[i + key[j] - 1];
		}
		for (int j = 0; j < lengthKey; j++) {
			dec_text += tmp[j];
		}
	}
	int textLength = (int)dec_text.length() - added_char;				//Длина начального текста
	for (int i = (int)dec_text.length() - 1; i >= textLength; i--) {	//Удаляю лишние символы
		dec_text.erase(i);		
	}

	return dec_text;
}

void encryption_decryption_file(string path, int key) {
	fstream file(path);
	string text;
	string line;

	if (!file) {
		cout << "File not found!" << endl;
	}
	else {
		while (getline(file, line)) {
			text += line;
			if (!file.eof()) {
				text += '\n';
			}
		}
	}
	if (key == 1) {
		text = encryption_text(text);
	}
	else if (key == 2) {
		text = decryption_text(text);
	}
	file.close();

	file.open(path, ios::out);
	if (!file) {
		cout << "File not found!" << endl;
	}
	else {
		file << text;
	}
	file.close();
}

int charToInt(char c) {
	return c - '0';
}


