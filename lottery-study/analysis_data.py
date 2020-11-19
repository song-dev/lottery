import csv
import unittest
from collections import Counter

from config import *


class MyTestCase(unittest.TestCase):

    def test_repetition(self):
        """
        测试连续性
        :return:
        """
        data = list()
        red = list()
        with open(UNION_LOTTO_FILE, 'r') as f:
            reader = csv.DictReader(f)
            for line in reader:
                data.append(line[UNION_LOTTO_BOLL[0]] + line[UNION_LOTTO_BOLL[1]] + line[UNION_LOTTO_BOLL[2]]
                            + line[UNION_LOTTO_BOLL[3]] + line[UNION_LOTTO_BOLL[4]] + line[UNION_LOTTO_BOLL[5]]
                            + line[UNION_LOTTO_BOLL[6]])
                red.append(line[UNION_LOTTO_BOLL[0]] + line[UNION_LOTTO_BOLL[1]] + line[UNION_LOTTO_BOLL[2]]
                           + line[UNION_LOTTO_BOLL[3]] + line[UNION_LOTTO_BOLL[4]] + line[UNION_LOTTO_BOLL[5]])
        print(data)
        print(Counter(data))
        print(red)
        print(Counter(red))
        self.assertTrue(True)

    def test_same_lotto(self):
        """
        篮球和红球相同
        :return:
        """
        data = list()
        analysis = list()
        with open(UNION_LOTTO_FILE, 'r') as f:
            reader = csv.DictReader(f)
            for line in reader:
                item = [line[UNION_LOTTO_BOLL[0]], line[UNION_LOTTO_BOLL[1]], line[UNION_LOTTO_BOLL[2]],
                        line[UNION_LOTTO_BOLL[3]], line[UNION_LOTTO_BOLL[4]], line[UNION_LOTTO_BOLL[5]],
                        line[UNION_LOTTO_BOLL[6]]]
                data.append(item)
        print(data)
        for item in data:
            a = [item[0], item[1], item[2], item[3], item[4], item[5]]
            if item[6] in a:
                analysis.append(item[6])
        print(len(analysis) / len(data))
        # 蓝球不和红球不相同 82%
        self.assertEqual(True, True)

    def test_odd_even(self):
        """
        测试奇偶连续
        :return:
        """
        data = list()
        analysis_6 = list()
        analysis_7 = list()
        with open(UNION_LOTTO_FILE, 'r') as f:
            reader = csv.DictReader(f)
            for line in reader:
                item = [line[UNION_LOTTO_BOLL[0]], line[UNION_LOTTO_BOLL[1]], line[UNION_LOTTO_BOLL[2]],
                        line[UNION_LOTTO_BOLL[3]], line[UNION_LOTTO_BOLL[4]], line[UNION_LOTTO_BOLL[5]],
                        line[UNION_LOTTO_BOLL[6]]]
                data.append(item)
        for item in data:
            count = 0
            for i in range(len(item)):
                if int(item[i]) % 2 == 0:
                    count += 1
            if count == 1 or count == 0 or count == 6 or count == 7:
                analysis_6.append(item)
        for item in data:
            count = 0
            for i in range(len(item) - 1):
                if int(item[i]) % 2 == 0:
                    count += 1
            if count == 1 or count == 0 or count == 6 or count == 5:
                analysis_7.append(item)
        print(len(analysis_6) / len(data))
        print(len(analysis_7) / len(data))
        # 红球奇偶相差个数最大为 2 为 83%
        # 所有球奇偶相差个数最大为 2 为 91%
        self.assertEqual(True, True)

    def test_continuity(self):
        """
        测试连续性能
        :return:
        """
        data = list()
        analysis_6 = list()
        with open(UNION_LOTTO_FILE, 'r') as f:
            reader = csv.DictReader(f)
            for line in reader:
                item = [line[UNION_LOTTO_BOLL[0]], line[UNION_LOTTO_BOLL[1]], line[UNION_LOTTO_BOLL[2]],
                        line[UNION_LOTTO_BOLL[3]], line[UNION_LOTTO_BOLL[4]], line[UNION_LOTTO_BOLL[5]]]
                data.append(item)
        for item in data:
            s = [int(x) for x in item]
            analysis_6.append(matching(s))
        print(Counter(analysis_6))
        print(len(analysis_6))
        # ({2: 1423, 0: 917, 3: 252, 4: 26, 5: 2})
        # ({2: 1423 54%, 0: 917 35%, 3: 252 9%, 4: 26 1%, 5: 2})
        analysis_6.clear()
        for i in range(len(data) - 1):
            s = [int(x) for x in data[i]]
            s1 = [int(x) for x in data[i + 1]]
            # if matching(s) == 0 and matching(s1) == 0:
            #     analysis_6.append(1)
            if matching(s) == 0:
                if matching(s1) > 0:
                    analysis_6.append(1)
                else:
                    analysis_6.append(0)
        print(Counter(analysis_6))
        print(len(analysis_6))
        # 上期连续，这期连续 66%，这期不连续 34%
        # 上期不连续，这期连续 63%，这期不连续 37%
        # 上期连续，这期不连续概率 22%
        # 上期不连续，这期连续概率 22%
        # 上期连续，这期连续概率 43%
        # 上期不连续，这期不连续概率 12%
        self.assertEqual(True, True)

    def test_blue_ball(self):
        """
        测试蓝球
        :return:
        """
        data = list()
        analysis_6 = list()
        with open(UNION_LOTTO_FILE, 'r') as f:
            reader = csv.DictReader(f)
            for line in reader:
                data.append(line[UNION_LOTTO_BOLL[5]])
        data = [int(x) for x in data]
        for i in range(len(data) - 1):
            if data[i] != data[i + 1]:
                analysis_6.append(1)
        print(len(analysis_6))
        # 连续两期蓝球不相同概率 89%
        self.assertEqual(True, True)

    def test_distribution(self):
        """
        测试号码分布情况
        :return:
        """
        data = list()
        analysis = list()
        with open(UNION_LOTTO_FILE, 'r') as f:
            reader = csv.DictReader(f)
            for line in reader:
                item = [line[UNION_LOTTO_BOLL[0]], line[UNION_LOTTO_BOLL[1]], line[UNION_LOTTO_BOLL[2]],
                        line[UNION_LOTTO_BOLL[3]], line[UNION_LOTTO_BOLL[4]], line[UNION_LOTTO_BOLL[5]],
                        line['期数']]
                # item = [int(x) for x in range(len(item) - 1)]
                data.append(item)
        # for i in range(len(data) - 1):
        #     if data[i] != data[i + 1]:
        #         analysis_6.append(1)
        for x in data:
            count = 0
            for i in range(len(x) - 1):
                if 23 <= int(x[i]):
                    count += 1
                # if 12 <= int(x[i]) <= 22:
                #     count += 1
            if count == 2:
                analysis.append(x)
        print(analysis)
        print(len(analysis))
        self.assertEqual(True, True)


def matching(item):
    if item[0] + 1 == item[1] \
            and item[1] + 1 == item[2] \
            and item[2] + 1 == item[3] \
            and item[3] + 1 == item[4] \
            and item[4] + 1 == item[5]:
        return 6
    elif (item[0] + 1 == item[1] and item[1] + 1 == item[2] and item[2] + 1 == item[3] and item[3] + 1 == item[4]) or (
            item[1] + 1 == item[2] and item[2] + 1 == item[3] and item[3] + 1 == item[4] and item[4] + 1 == item[5]):
        return 5
    elif (item[0] + 1 == item[1] and item[1] + 1 == item[2] and item[2] + 1 == item[3]) \
            or (item[1] + 1 == item[2] and item[2] + 1 == item[3] and item[3] + 1 == item[4]) \
            or (item[2] + 1 == item[3] and item[3] + 1 == item[4] and item[4] + 1 == item[5]):
        return 4
    elif (item[0] + 1 == item[1] and item[1] + 1 == item[2]) \
            or (item[1] + 1 == item[2] and item[2] + 1 == item[3]) \
            or (item[2] + 1 == item[3] and item[3] + 1 == item[4]) \
            or (item[3] + 1 == item[4] and item[4] + 1 == item[5]):
        return 3
    elif (item[0] + 1 == item[1]) \
            or (item[1] + 1 == item[2]) \
            or (item[2] + 1 == item[3]) \
            or (item[3] + 1 == item[4]) \
            or (item[4] + 1 == item[5]):
        return 2
    else:
        return 0


if __name__ == '__main__':
    unittest.main()
    # 双色球重复概率为 0，红球重复出现次数仅为 2 次
    # 蓝球和红球不相同概率 82%
    # 红球奇偶相差个数最大为 2 为 83%
    # 所有球奇偶相差个数最大为 2 为 91%
    # 最多2个球连续概率 90%
    # 连续两期蓝球不相同概率 89%
