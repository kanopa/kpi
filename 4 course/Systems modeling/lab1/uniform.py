import numpy as np
import matplotlib.pyplot as plt
from scipy.stats import kstest
from plotHelper import scatterplot

a = pow(5, 13)
c = pow(2, 31)
z = 2
x2Nab = 0
X2Krit = 37.57

randNumbers = []

for i in range(10000):
    z = (a * z) %c
    randNumbers.append(z / c)

test = kstest(randNumbers, 'uniform')

n, x, bars = plt.hist(randNumbers, bins = 20)
plt.show()

result = zip(x, n)

xn = []
for item in result:
    xn.append(item[0] * item[1])

mean = sum(xn) / sum(n)
print("Avarage: ", mean)

xnT = []
i = 0
dispSum = 0
while i < len(xn):
    value = pow((x[i] - mean), 2) * n[i]
    xnT.append(value)
    dispSum += value
    i += 1

disp = dispSum / (sum(n))
print("Dispersion: ", disp)

otklonenie = np.sqrt(disp)

aStar = mean - np.sqrt(3) * otklonenie
bStar = mean + np.sqrt(3) * otklonenie

randNumbersY = []

for z in randNumbers:
    randNumbersY.append(1 / (bStar - aStar))

scatterplot(randNumbers, randNumbersY)

plotnost = 1 / (bStar - aStar)
xdiff = np.diff(x)
h = np.mean(xdiff)
h = h / 2

nFirst = sum(n) * plotnost * (x[0] + h - aStar)
nLast = sum(n) * plotnost * (bStar - (x[len(x) - 1] - h))

ns = []
ns.append(nFirst)
i = 1
while i < len(x) - 1:
    ns.append(sum(n) * plotnost * (x[i+1] - x[i]))
    i += 1
ns.append(nLast)

i = 0
while i < len(n):
    x2Nab += pow(n[i] - ns[i], 2) / ns[i]
    i += 1

print("X2Krit", X2Krit)
print("x2Nab", x2Nab)