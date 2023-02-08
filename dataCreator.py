import random

def main():
    print("Create or overwrite an existing text file")
    print("Numbers in the range 1 - 100 will be generated\n")

    fileName = input("Enter the file name: ")
    numRange = int(input("Enter the total amount of numbers to be generated: "))

    saveFile = open(fileName, 'w')

    for x in range(numRange):
        randomNum = random.randint(1,101)
        saveFile.write(str(randomNum) + "\n")

    saveFile.close()
    if numRange == 1:
        print("\n1 number has been generated into the file: " + fileName)
    else:    
        print("\n" + str(numRange) + " numbers have been generated " \
              "into file: " + fileName)

main()
