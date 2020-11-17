import PySimpleGUI as sg
import numpy


def shift(arr, steps):
    if steps < 0:
        steps = abs(steps)
        for _ in range(steps):
            arr.append(arr.pop(0))
    else:
        for _ in range(steps):
            arr.insert(0, arr.pop())


def oneRoundEncrypt(rightBlock, leftBlock, key):
    shift(rightBlock, -4)
    shiftedKey = []
    shiftedKey.extend(key)
    shift(shiftedKey, -3)
    resFunction = []                                              # f(x,k) = (x<<4)⊕(k<<3)⊕k

    for i in range(len(key)):
      resFunction.append(rightBlock[i] ^ shiftedKey[i] ^ key[i])

    rightBlock.clear()
    for i in range(len(key)):
      rightBlock.append(leftBlock[i] ^ resFunction[i])

    pass


def oneRoundDecrypt(rightBlock, leftBlock, key):
    shift(leftBlock, -4)
    shiftedKey = []
    shiftedKey.extend(key)
    shift(shiftedKey, -3)
    resFunction = []                                              # f(x,k) = (x<<4)⊕(k<<3)⊕k

    for i in range(len(key)):
      resFunction.append(leftBlock[i] ^ shiftedKey[i] ^ key[i])

    leftBlock.clear()
    for i in range(len(key)):
      leftBlock.append(rightBlock[i] ^ resFunction[i])

    pass


def encrypt(filepath):
    
    key = numpy.random.randint(2, size=8)

    bytesFromFile = numpy.fromfile(filepath, dtype="uint8")
    bits = numpy.unpackbits(bytesFromFile)

    remainder = len(bits) % 16
    lenWithoutRemainder = len(bits) - remainder
    encBits = []
    counter = 0

    while(counter < lenWithoutRemainder):
        oneBlock = []                               #16 bits

        for i in range(counter, counter + 16, 1):
            oneBlock.append(bits[i])

        leftBlock = []
        rightBlock = []

        for i in range(0, 8, 1):
            leftBlock.append(oneBlock[i])

        for i in range(8, 16, 1):
            rightBlock.append(oneBlock[i])

        round = 1
        while round <= 3:
            tmpLeftBlock = []
            tmpLeftBlock.extend(leftBlock)
            leftBlock.clear()
            leftBlock.extend(rightBlock)
            oneRoundEncrypt(rightBlock, tmpLeftBlock, key)
            round += 1      

        encBits.extend(leftBlock)
        encBits.extend(rightBlock)
        
        counter += 16

    for i in range(lenWithoutRemainder, len(bits), 1):
        encBits.append(bits[i])

    encBits.extend(key)

    encBytes = numpy.packbits(encBits)

    with open(filepath, "wb") as file:
        file.write(encBytes)

    pass


def decrypt(filepath):
    bytesFromFile = numpy.fromfile(filepath, dtype="uint8")
    bits = []
    bits.extend(numpy.unpackbits(bytesFromFile))

    decKey = []
    for _ in range(len(bits) - 8, len(bits), 1):
      decKey.insert(0, bits.pop())

    remainder = len(bits) % 16
    lenWithoutRemainder = len(bits) - remainder
    decBits = []
    counter = 0
    while(counter < lenWithoutRemainder):
        oneBlock = []                               #16 bits

        for i in range(counter, counter + 16, 1):
            oneBlock.append(bits[i])

        leftBlock = []
        rightBlock = []

        for i in range(0, 8, 1):
            leftBlock.append(oneBlock[i])

        for i in range(8, 16, 1):
            rightBlock.append(oneBlock[i])

        round = 1
        while round <= 3:
            tmpRightBlock = []
            tmpRightBlock.extend(rightBlock)
            rightBlock.clear()
            rightBlock.extend(leftBlock)
            oneRoundDecrypt(tmpRightBlock, leftBlock, decKey)
            round += 1      

        decBits.extend(leftBlock)
        decBits.extend(rightBlock)
        
        counter += 16

    for i in range(lenWithoutRemainder, len(bits), 1):
        decBits.append(bits[i])

    decBytes = numpy.packbits(decBits)

    with open(filepath, "wb") as file:
        file.write(decBytes)

    pass


layout = [
    [sg.Text('File'), sg.InputText(key=0), sg.FileBrowse()],
    [sg.Radio('Encrypt', "RADIO1", key=1),
     sg.Radio('Decrypt', "RADIO1", key=2)],
    [sg.Output(size=(55, 5))],
    [sg.Submit(), sg.Cancel()]
]
window = sg.Window('FeistelCipher', layout)
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
