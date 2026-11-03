from matplotlib import pyplot as plt
import csv

# initialize lists
sizes = []
ratios = []

# read sizes and compression ratios from csv file
with open("src/main/resources/compressionRatios.csv", "r") as f:
    data = csv.DictReader(f)
    for row in data:
        sizes.append(int(row['size']))
        ratios.append(float(row['ratio']))

# plot data
plt.figure()
plt.plot(sizes, ratios)
plt.title('Compression Ratio vs String Length')
plt.xlabel('String Length')
plt.ylabel('Compression Ratio')
plt.savefig('compression_ratios.png')