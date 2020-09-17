import random
import numpy as np
import matplotlib.pyplot as plt
from plotHelper import scatterplot

randNumbers = []
randNumbersY = []
lamb = 0.01
x2Nab = 0
X2Krit = 37.57

for that in range(10000):
    randNumbers.append(random.uniform(0.001,1))

i = 0
while i < len(randNumbers):
    randNumbers[i] = (-1 / lamb) * np.log(randNumbers[i])
    i+=1

k = 0
while k < len(randNumbers):
    randNumbersY.append(1 - np.exp(-1 * lamb * randNumbers[k]))
    k+=1

scatterplot(randNumbers, randNumbersY, "MyNumbers", "FXNumbers", "Plot")

n, x, bars = plt.hist(randNumbers, bins = 20)

result = zip(x, n)

plt.show()


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

disp = dispSum / (sum(n))
print("Dispersion: ", disp)

p = []
i = 0
while i < len(x) - 1:
    p.append(np.exp(- lamb * x[i]) - np.exp(- lamb * x[i+1]))
    i += 1

ni = []
i = 0
while i < len(p):
    ni.append(sum(n) * p[i])
    i += 1

result = []
i = 0
while i < len(p):
    result.append(pow(n[i] - ni[i], 2) / ni[i])
    i += 1

x2Nab = sum(result)


print("X2Krit ", X2Krit)
print("x2Nab ", x2Nab)