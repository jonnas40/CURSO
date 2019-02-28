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
    y = f(0)
    fin1 = cons * y
    fin = 0
    for k in range(1, 500):
        ser = f(k)
        fin2 = ser
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
    totalf = 0
    Rn = 1 / (1 - fin)

    for j in range(100):
        ser = f(j)
        totalf = totalf + ser
        func = cons * totalf
        Al = ser * Rn
        acc = acc + 1
        erro = math.fabs(math.pi - func)

        if Al < exp:
            print("O n-ésimo termo com erro absoluto inferior a ", "{:.0E}".format(exp), " é: ", j,
                  ", com o valor aproximado de: ", func,". E o erro absoluto: ",erro)
            exp = exp / 10
            if exp < 1.e-15:
                break



def cauchy(cons, serie):
    print("Critério de Cauchy")
    f = lambda x: eval(serie)
    ter = 0
    acc = 0
    exp = 1.e-8
    totalf = 0

    for k in range(0, 10000):
        ser = f(k)
        totalf = totalf + ser
        #print(math.fabs(ser))
        if math.fabs(ser) <= exp:
            total = cons * totalf
            erro = math.fabs(math.pi - total)
            print("O n-ésimo termo com erro absoluto inferior a ", "{:.0E}".format(exp), " é: ", k,
                  ", com o valor aproximado de: ", total, ". E o erro absoluto: ", erro)
            exp = exp / 10
            if exp < 1.e-15:
                break


""""
    for j in range(100):
        Rn = math.pow(ter, j - 1) / 1 - ter
        acc = acc + 1
        ser = f(j)
        totalf = totalf + ser
        total = cons * totalf
        erro = math.fabs(math.pi - total)


        if Rn < exp:
            """


cons = (9 / (2 * math.sqrt(3)))
serie = "((math.factorial(x))**2) / (math.factorial((2*x)+1))"
dalambert(cons, serie)
print()
cons1 = 4
serie1 = "(((-1)**(x))/((2*(x))+1))"
cauchy(cons1, serie1)
