from collections import deque, defaultdict
from typing import List

class Router:

    def __init__(self, memoryLimit: int):
        self.memoryLimit = memoryLimit
        self.queue = deque()  # store packets in FIFO order
        self.seen = set()     # to detect duplicates (source, destination, timestamp)
        self.dest_map = defaultdict(list)  # destination -> list of timestamps

    def addPacket(self, source: int, destination: int, timestamp: int) -> bool:
        packet_key = (source, destination, timestamp)

        # Duplicate check
        if packet_key in self.seen:
            return False

        # If memory is full, evict oldest packet
        if len(self.queue) >= self.memoryLimit:
            old_source, old_dest, old_time = self.queue.popleft()
            self.seen.remove((old_source, old_dest, old_time))
            # also pop from destination map (timestamps are increasing)
            self.dest_map[old_dest].pop(0)

        # Add new packet
        self.queue.append((source, destination, timestamp))
        self.seen.add(packet_key)
        self.dest_map[destination].append(timestamp)
        return True

    def forwardPacket(self) -> List[int]:
        if not self.queue:
            return []
        
        source, destination, timestamp = self.queue.popleft()
        self.seen.remove((source, destination, timestamp))
        # remove first timestamp for this destination
        self.dest_map[destination].pop(0)
        return [source, destination, timestamp]

    def getCount(self, destination: int, startTime: int, endTime: int) -> int:
        if destination not in self.dest_map:
            return 0

        timestamps = self.dest_map[destination]

        # Binary search to count timestamps in [startTime, endTime]
        import bisect
        left = bisect.bisect_left(timestamps, startTime)
        right = bisect.bisect_right(timestamps, endTime)
        return right - left
