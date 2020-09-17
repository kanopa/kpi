import random
import numpy as np
import matplotlib.pyplot as plt
from scipy import stats
from plotHelper import scatterplot

randNumbers = []
randNumbersY = []
sigma = 1
alpha = 0.01
x2Nab = 0
X2Krit = 37.57

for that in range(10000):
    miu = -6
    for i in range(12):
        miu+= random.uniform(0.001,1)
    randNumbers.append(sigma * miu + alpha)

k = 0
while k < len(randNumbers):
    randNumbersY.append( (1 / (sigma * np.sqrt(2 * np.pi))) * np.exp(- (pow(randNumbers[k] - alpha,2) / (2 * pow(sigma, 2)))))
    k+=1

scatterplot(randNumbers, randNumbersY, "MyNumbers", "FXNumbers", "Plot")

n, x, bars = plt.hist(randNumbers, bins = 20)
plt.show()

result = zip(x, n)

xn = []
for item in result:
    xn.append(item[0] * item[1])

mean = sum(xn) / sum(n)
print("Avarage: ", mean)

i = 0
dispSum = 0
while i < len(xn):
    dispSum += pow((mean - x[i]), 2) * n[i]
    i += 1

disp = dispSum / (sum(n) - 1)
print("Dispersion: ", dispSum)

otklonenie = np.sqrt(disp)

h = (max(x) - min(x)) / len(n)
print("H: ", h)

u = []
i = 0
while i < len(x):
    u.append((x[i]-mean) / otklonenie)
    i += 1

fu = []
for item in u:
    fu.append(np.exp(-pow(item, 2) / 2) / np.sqrt(2*np.pi))

ni0 = []
for item in fu:
    ni0.append((sum(n) * h * item) / otklonenie)

for i in range(len(n)):
    x2Nab += pow((n[i] - ni0[i]), 2) / ni0[i]

print("X2Krit", X2Krit)
print("x2Nab ", x2Nab)