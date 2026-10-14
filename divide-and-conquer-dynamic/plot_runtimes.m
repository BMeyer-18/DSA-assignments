data = table2array(readtable('./src/main/resources/runtimeData.csv'));
size = data(:,1); regular = data(:,2); strassen = data(:,3); hybrid = data(:,4);

f1 = figure;
loglog(size, regular); hold on
loglog(size, strassen)
loglog(size, hybrid)
legend('Regular Algorithm', 'Strassen Algorithm', 'Hybrid Algorithm', interpreter='latex', location='northwest')
title('Runtime vs Size for Matrix Multiplication Algorithms', interpreter='latex')
xlabel('Size of Matrix', interpreter='latex')
ylabel('Runtime (s)', interpreter='latex')

exportgraphics(f1, './runtimes.png')