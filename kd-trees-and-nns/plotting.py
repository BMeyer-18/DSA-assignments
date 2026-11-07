import pandas as pd
import numpy as np
from matplotlib import pyplot as plt

# load csv as dataframe
df = pd.read_csv('src/main/resources/NNS_Benchmarks.csv')

# find all unique dimensionality values and iterate through them.
dim_vals = np.unique(df['Dims'].values).tolist()
df_dims = []
for dim in dim_vals:
    df_dims.append(df[df["Dims"] == dim])

plots = df.columns.to_list()[2:5]
for p in range(len(plots)):
    plt.figure()
    for i in range(len(dim_vals)):
        points = df_dims[i]['Points'].values.tolist()
        times = df_dims[i][plots[p]].values.tolist()
        plt.plot(points, times, label=f'{dim_vals[i]} dims')
    plt.xscale('log')
    plt.yscale('log')
    plt.xlabel('# of Points')
    plt.ylabel('Time (s)')
    plt.title(f'Runtime vs Number of Points for {plots[p]}')
    plt.legend()
    plt.gca().set_ylim([10**(-5), 10**2])
    plt.savefig(f'graphics/{plots[p]}.png')