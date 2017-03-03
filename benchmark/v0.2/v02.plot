# Set the file to have format jpeg and size 1280x720
set terminal jpeg size 1280,720

# aspect ratio of the graph
set size 1, 1

# write the data to
set output "benchmark/v0.2/v02.jpeg"

# title
set title "Benchmark - 1000 calls, no concurrent calls, 96MB file"

# legend placement
set key left top

# gridlines are oriented on the y axis
set grid y

# x-series data is time data
set xdata time

# time in the y axis
set timefmt "%s"

# time in the x axis
set format x "%H:%M:%S"

# Label the x-axis
set xlabel 'seconds'

# Label the y-axis
set ylabel "response time (ms)"

# Tell gnuplot to use tabs as the delimiter instead of spaces (default)
set datafile separator '\t'

# Plot the data
plot "benchmark/v0.2/v02.tsv" every ::2 using 2:5 title 'response time' with points
exit
