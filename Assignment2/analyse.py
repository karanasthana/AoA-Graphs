import csv
import pandas
import datetime as dt
import matplotlib.pyplot as plt
from dateutil.relativedelta import relativedelta
from operator import itemgetter
from numpy import NaN
from IPython.display import display
from statistics import mode

headerIndexDict = {}
spacedDateFormat = '%b %d, %Y'
slashedDateFormat = '%m/%d/%Y'

def readCSVFile():
    rows = []

    with open("/Users/karanasthana/Documents/UFL/AOA/Assigments/Assignment2/data.csv", 'r') as file:
        csvreader = csv.reader(file)
        for row in csvreader:
            rows.append(row)
    header = ['num_vertices', 'runtime', 'type'];
    return header, rows

def showDataOnGraph(rows):
    for row in rows:
        if (int)(row[1]) > 10000000:
            continue
        if row[2] == '100%':
            plt.scatter((int)(row[0]), (int)(row[1]), color='blue')
        elif row[2] == '50%':
            plt.scatter((int)(row[0]), (int)(row[1]), color='orange')
        elif row[2] == '25%':
            plt.scatter((int)(row[0]), (int)(row[1]), color='black')

    plt.show()

def main():
    header, rows = readCSVFile()
    showDataOnGraph(rows)

main()