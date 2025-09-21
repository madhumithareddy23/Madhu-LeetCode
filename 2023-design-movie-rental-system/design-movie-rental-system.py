from sortedcontainers import SortedList
from collections import defaultdict

class MovieRentingSystem:

    def __init__(self, n: int, entries: List[List[int]]):
        # price lookup
        self.price_map = {}
        
        # unrented[movie] -> sorted list of (price, shop)
        self.unrented = defaultdict(SortedList)
        
        # rented -> sorted list of (price, shop, movie)
        self.rented = SortedList()
        
        # fill initial data
        for shop, movie, price in entries:
            self.price_map[(shop, movie)] = price
            self.unrented[movie].add((price, shop))

    def search(self, movie: int) -> List[int]:
        # return cheapest 5 shops with unrented movie
        return [shop for price, shop in self.unrented[movie][:5]]

    def rent(self, shop: int, movie: int) -> None:
        price = self.price_map[(shop, movie)]
        # remove from unrented
        self.unrented[movie].remove((price, shop))
        # add to rented
        self.rented.add((price, shop, movie))

    def drop(self, shop: int, movie: int) -> None:
        price = self.price_map[(shop, movie)]
        # remove from rented
        self.rented.remove((price, shop, movie))
        # add back to unrented
        self.unrented[movie].add((price, shop))

    def report(self) -> List[List[int]]:
        # return cheapest 5 rented movies
        return [[shop, movie] for price, shop, movie in self.rented[:5]]
