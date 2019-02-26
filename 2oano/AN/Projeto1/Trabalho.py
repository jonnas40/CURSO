import math


# Exercicio 1
def epsilon():
    print("Epsilon maquina")
    eps = 1
    while eps + 1 > 1:
        eps = eps / 2
    print(eps)


# Exercicio 2

def dalambert(cons, serie):
    print("Criterio de D'Alembert")
    f = lambda x: eval(serie)
    y = f(1)
    fin1 = cons * y
    fin = 0
    for k in range(2, 500):
        ser = f(k)
        fin2 = cons * ser
        # print(k)
        fin = fin2 / fin1
        # print(fin)
        fin1 = fin2

    if fin > 1:
        print("A serie diverge")
    elif fin == 1:
        print("Nada se pode concluir")
        return

    acc = 0
    exp = 1.e-8
    total = 0

    for j in range(100):
        ser = f(j)
        func = cons * ser
        Rn = 1 / (1 - fin)
        Al = func * Rn
        acc = acc + 1
        total = total + f(j)
        erro = math.pi - total
        if erro < 0:
            erro = -erro

        if Al < exp:
            n = j
            print("O n-ésimo termo com erro absoluto inferior a ", "{:.0E}".format(exp), " é: ", j,
                  ", com o valor aproximado de: ", total,". E o erro absoluto: ",erro)
            exp = exp / 10
            if exp < 1.e-15:
                break


def cauchy(cons, serie):
    print("Critério de Cauchy")
    f = lambda x: eval(serie)
    ter = 0
    for k in range(1, 500):
        ser = f(k)
        ter1 = cons * ser
        #k1 = 1/float(k)
        ter = ter1**(1/float(k))
        #ter = math.pow(ter1, k1)
        ter = ter.real

        if ter > 1:
            print("A serie diverge")
        elif ter == 1:
            print("Nada se pode concluir")
            return

    acc = 0
    exp = 1.e-8
    total = 0

    for j in range(100):
        Rn = math.pow(ter, j - 1) / 1 - ter
        acc = acc + 1
        total = total + f(j)
        erro = math.fabs(math.pi - total)


        if Rn < exp:
            n = j
            print("O n-ésimo termo com erro absoluto inferior a ", "{:.0E}".format(exp), " é: ", j, ", com o valor aproximado de: ", total,". E o erro absoluto: ",erro)
            exp = exp / 10
            if exp < 1.e-15:
                break


cons = (9 / (2 * math.sqrt(3)))
serie = "(math.factorial(x)**2) / (math.factorial(2*x+1))"
dalambert(cons, serie)
print()
cons1 = 4
serie1 = "(((-1)**(x))/(2*(x)+1))"
cauchy(cons1, serie1)
