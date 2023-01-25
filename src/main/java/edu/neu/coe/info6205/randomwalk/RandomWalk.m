% Defining data for steps and mean distance %
m = [1 4 9 16 25 36 49 64 81 100];
d_observed = [1.0 1.756 2.685 3.543 4.439 5.318 6.211 7.081 7.992 8.88];

% Plotting observed euclidean distance data points with red circles %
plot(m, d_observed, '-ro')
hold on
% Plotting hypothesis for expression for distance in same plot in blue %
plot(m, sqrt(m)*0.875, 'b')

% Adding labels and descriptions to plot %
xlabel('steps (m)')
ylabel('distance (d)')
title('distance vs steps (averaged over 100,000 trials)')
markerColor = get(gca,'Children').MarkerFaceColor;
legend('Observation : d_o', 'Hypothesis: d_h = 0.875*sqrt(m)','Color',markerColor);

% Defining data for observed mean squared distance %
d_squared_observed = [1 4.00 9.02 16.06 25.03 35.78 49.12 63.80 81.11 99.53]

% Opening new figure and plotting data
figure;
plot(m, d_squared_observed, '-ro')
hold on
plot(m, m, 'b')
xlabel('steps (m)')
ylabel('squared distance (d^2)')
title('distance squared vs steps (averaged over 100,000 trials)')
markerColor = get(gca,'Children').MarkerFaceColor;
legend('Observation : d^2_o', 'Hypothesis: d^2_h = m','Color',markerColor);
