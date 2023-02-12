x = [100 200 400 800 1600 3200 6400 12800 25600 51200 102400 204800 409600 819200 1638400]; % Sites (n)
y = [253.27 579.13 1420.40 2878.07 6500.07 13873.67 29782.53 69229.67 137495.60 300831.00 616591.00 1302929.33 2844223.47 5583123.60 11995914.73]; % Pairs(m)

% Define the custom model function (Supsected linearithmic - nlogn)
model_fun = @(b,x) b(1)*(x.*(log(x)/log(exp(1))));

% Fit the model to the data
beta0 = [0.5]; % initial parameter estimates
beta = nlinfit(x,y,model_fun,beta0);

% Evaluate the model at the x-values
yfit = model_fun(beta,x);

% Plot the data and the fit
plot(x,y,'o',x,yfit,'-')
xlabel('Sites (n)')
ylabel('Pairs (m)')
legend('Observed Pair Data','0.5*n*ln(n)')
title("Average Pairs vs Sites (over 30 runs)")


