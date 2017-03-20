# This is a trivial implementation of a supermarket

Cart class holds Pack(s) of Item(s)

PackPriceDiscounter is using a PackPriceReviser to calculate the discounted price for a Pack

CartPriceCalculator can use PackPriceDiscounter to mutate its state (update Pack(s) prices)

#I wrote it this way because it was suggested to me that it would be nice to get it done before Monday, so here it is.
#For a real-life task I would use a different approach, where you have an immutable Cart you can add discounts to and
#the discounts would get applied lazily during the checkout while providing a preview of the final price.

