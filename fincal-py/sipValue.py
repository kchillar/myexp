
def monthlySipValue(noOfTimeIntervals,ratePerYear,monthlySipAmount):
        monthlyRate=ratePerYear/(12*100)
        monthlyFactor = 1.0 + monthlyRate
        a=monthlySipAmount
        f=1.0
        for t in range(noOfTimeIntervals):
                f = f*monthlyFactor
        a=a*f
        print("Intervals: %2d, Factor: %2.4f, Amount: %10.2f" %(noOfTimeIntervals,f,a))                
        return a

def monthlySipValueWithAmounts(noOfTimeIntervals,ratePerYear,monthlySipAmount, amountsArr):
        localAmounts = []
        amountsArr.append(localAmounts)
        monthlyRate=ratePerYear/(12*100)
        monthlyFactor = 1.0 + monthlyRate
        a=monthlySipAmount
        f=1.0
        for t in range(noOfTimeIntervals):
                f = f*monthlyFactor
                localAmounts.append(a*f)
        a=a*f
        print("Intervals: %2d, Factor: %2.4f, Amount: %10.2f" %(noOfTimeIntervals,f,a))                
        return a

def showAmountArr(arr):
        print("------------ Amount Details -------------")
        for subarr in arr:
                for amt in subarr:
                        print("%10.2f" %amt, end=' ')
                print("")        


time = int(input("Enter number of years of investment: "))
rate = float(input("Enter expected average interest percent per year:"))
sipAmount = float(input("Enter the monthly SIP amount: "))

intervals = time*12
induvidualAmounts = []
total = 0
for t in range(intervals):
        amount = monthlySipValueWithAmounts(intervals -t ,rate,sipAmount, induvidualAmounts)
        total = total + amount;


print("Time: %2d, Rate: %2.2f, SIPAmount: %10.2f, Total:%10.2f" %(time, rate, sipAmount,total))        

showAmountArr(induvidualAmounts)


