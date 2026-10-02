data = table2array(readtable('./src/main/resources/runtimeData.csv'));
size = data(:,1); heap = data(:,2); radix = data(:,3); merge = data(:,4); insertion = data(:,5);

f1 = figure;
loglog(size, heap); hold on
loglog(size, radix)
loglog(size, merge)
loglog(size, insertion)
legend('Heap Sort', 'Radix Sort', 'Merge Sort', 'Insertion Sort', interpreter='latex', location='northwest')
title('Runtime vs Size for Four Sorting Algorithms', interpreter='latex')
xlabel('Size of List', interpreter='latex')
ylabel('Runtime (s)', interpreter='latex')

exportgraphics(f1, './graphs/comparison.png')


f2 = figure;
subplot(2,2,1)
loglog(size, heap); hold on
loglog(size, 1e-8*size.*log2(size), '--')
loglog(size, 1e-7*size.*log2(size), '--')
legend('Heap Sort', '$10^{-8}n lg(n)$', '$10^{-7}n lg(n)$', interpreter='latex', location='northwest')
title('Runtime vs Size for Heap Sort', interpreter='latex')
xlabel('Size of List', interpreter='latex')
ylabel('Runtime (s)', interpreter='latex')

subplot(2,2,2)
loglog(size, radix); hold on
loglog(size, 1e-6*size, '--')
loglog(size, 1e-4*size, '--')
legend('Radix Sort', '$10^{-6}n$', '$10^{-4}n$', interpreter='latex', location='northwest')
title('Runtime vs Size for Radix Sort', interpreter='latex')
xlabel('Size of List', interpreter='latex')
ylabel('Runtime (s)', interpreter='latex')

subplot(2,2,3)
loglog(size, merge); hold on
loglog(size, 1e-8*size.*log2(size), '--')
loglog(size, 1e-6*size.*log2(size), '--')
legend('Merge Sort', '$10^{-8}n lg(n)$', '10^{-6}n lg(n)$', interpreter='latex', location='northwest')
title('Runtime vs Size for Merge Sort', interpreter='latex')
xlabel('Size of List', interpreter='latex')
ylabel('Runtime (s)', interpreter='latex')

subplot(2,2,4)
loglog(size, insertion); hold on
loglog(size, 1e-9.*(size).^2, '--')
loglog(size, 1e-7.*(size).^2, '--')
legend('Insertion Sort', '$10^{-9}n^2$', '$10^{-7}n^2$', interpreter='latex', location='northwest')
title('Runtime vs Size for Insertion Sort', interpreter='latex')
xlabel('Size of List', interpreter='latex')
ylabel('Runtime (s)', interpreter='latex')

exportgraphics(f2, './graphs/complexity.png')