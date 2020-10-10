# n = 7, a = 5, b = 3
import PySimpleGUI as sg
import numpy


sBlock = [[4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1],
          [13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6],
          [1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2],
          [6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12]]


def binary(number):
    bits = ''
    while number > 0:
        bits = str(number % 2) + bits
        number = number // 2
    while(len(bits) < 4):
        bits = '0' + bits
    return bits


def encrypt(filepath):
    Bytes = numpy.fromfile(filepath, dtype="uint8")
    Bits = numpy.unpackbits(Bytes)

    excess_mod = len(Bits) % 6
    len_without_excess = len(Bits) - excess_mod
    enc_Bits = []
    counter = 0
    while(counter < len_without_excess):
        six_Bits = []

        for i in range(counter, counter + 6, 1):
            six_Bits.append(Bits[i])

        binary_row = str(six_Bits[5]) + str(six_Bits[3])
        column = str(six_Bits[0]) + str(six_Bits[1]) + \
            str(six_Bits[2]) + str(six_Bits[4])
        int_row = int(binary_row, 2)
        int_column = int(column, 2)

        number_sBlock = sBlock[int_row][int_column]
        binary_number_sBlock = binary(number_sBlock)

        enc_Bits.append(six_Bits[5])
        enc_Bits.append(six_Bits[3])

        for i in range(0, 4, 1):
            enc_Bits.append(int(binary_number_sBlock[i]))

        counter += 6

    for i in range(len_without_excess, len(Bits), 1):
        enc_Bits.append(Bits[i])

    enc_Byte = numpy.packbits(enc_Bits)

    with open(filepath, "wb") as file:
        file.write(enc_Byte)

    pass


def decrypt(filepath):
    Bytes = numpy.fromfile(filepath, dtype="uint8")
    Bits = numpy.unpackbits(Bytes)

    excess_mod = len(Bits) % 6
    len_without_excess = len(Bits) - excess_mod
    dec_Bits = []
    counter = 0
    while(counter < len_without_excess):
        six_Bits = []

        for i in range(counter, counter + 6, 1):
            six_Bits.append(Bits[i])

        binary_row = str(six_Bits[0]) + str(six_Bits[1])
        binary_number_sBlock = str(six_Bits[2]) + str(six_Bits[3]) + \
            str(six_Bits[4]) + str(six_Bits[5])
        int_row = int(binary_row, 2)
        int_column = 0
        number_sBlock = int(binary_number_sBlock, 2)

        for i in range(0, 16, 1):
            if sBlock[int_row][i] == number_sBlock:
                int_column = i

        binary_column = binary(int_column)

        dec_Bits.append(int(binary_column[0]))
        dec_Bits.append(int(binary_column[1]))
        dec_Bits.append(int(binary_column[2]))
        dec_Bits.append(int(binary_row[1]))
        dec_Bits.append(int(binary_column[3]))
        dec_Bits.append(int(binary_row[0]))

        counter += 6

    for i in range(len_without_excess, len(Bits), 1):
        dec_Bits.append(Bits[i])

    dec_Byte = numpy.packbits(dec_Bits)

    with open(filepath, "wb") as file:
        file.write(dec_Byte)

    pass


layout = [
    [sg.Text('File'), sg.InputText(key=0), sg.FileBrowse()],
    [sg.Radio('Encrypt', "RADIO1", key=1),
     sg.Radio('Decrypt', "RADIO1", key=2)],
    [sg.Output(size=(55, 5))],
    [sg.Submit(), sg.Cancel()]
]
window = sg.Window('S-Blocks', layout)
while True:
    event, values = window.read()
    if event in (None, 'Exit', 'Cancel'):
        break
    elif event == 'Submit':
        filepath = values[0]
        if values[1]:
            encrypt(filepath)
            print("Файл " + values[0] + " зашифрован!")
        elif values[2]:
            decrypt(filepath)
            print("Файл " + values[0] + " расшифрован!")
