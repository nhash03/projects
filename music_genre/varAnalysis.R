library(tidymodels)
library(tidyverse)
library(repr)
library(cowplot)
library(GGally)
library(caret)

set.seed(200)
#reading the data
url <- "https://raw.githubusercontent.com/nhash03/DSCI100/main/music_genre.csv"
data <- read_csv(url) %>%
          glimpse()

#cleaning the vectors type and n/a genres
data <- data%>%
  mutate (music_genre = as_factor(music_genre)) %>%
  filter(!is.na(music_genre)) %>%
  filter(tempo != "?") %>%
  mutate(tempo = as.double(tempo)) %>%
  mutate(key = as_factor(key)) %>%
  mutate(mode = as_factor (mode)) 
#cleaning the date column
data <- mutate(data,
              obtained_date = 
                as.Date(paste(data$obtained_date, "-21",sep = ""), 
                        "%d-%b-%y"))

#finding the number of tracks from each genre
genres_distribution <- data %>%
  group_by(music_genre) %>%
  summarise(count = n()) #shows that we have same n for all of the genres

genres_distribution <- genres_distribution %>%
  mutate(diff = count-min(count))

#plotting the total number of tracks from each genre and the difference of each of them from min
genres_distribution_1 <- 
  ggplot(genres_distribution, aes(x=diff,y=reorder(music_genre,diff))) +
  geom_col(fill = "orange") + 
  labs (y="music genre", x= "number of tracks (+4466)") 
genre_distribution_2 <- 
  ggplot(genres_distribution, aes(x=count,y=reorder(music_genre,count))) +
  geom_col(fill = "blue") + 
  labs (y="music genre", x= "number of tracks ")

genres_distribution_plot <- plot_grid(genres_distribution_1,genre_distribution_2,ncol=1)
genres_distribution_plot


#dataframe of mean of ecah variable for each genre
general_distribution <- data %>%
  group_by(music_genre) %>%
  summarise(avg_popularity = mean(popularity),
            avg_acousticness = mean(acousticness),
            avg_danceability = mean(danceability),
            avg_duration_ms = mean(duration_ms),
            avg_energy = mean(energy),
            avg_instrumentalness = mean(instrumentalness),
            avg_liveness = mean(liveness),
            avg_loudness = mean(loudness),
            avg_speechiness = mean(speechiness),
            avg_tempo = mean(tempo),
            avg_valence = mean(valence)) %>%
  glimpse()
            
general_distribution


#finding the most important variables to distinguish music genre
rrfMod <- train(music_genre ~ popularity+acousticness + danceability + duration_ms+
                  energy + instrumentalness + liveness + loudness +
                  speechiness +tempo +valence , data=data, method="rpart")
rrfImp <- varImp(rrfMod)

#use a sample of n=20 from each group to plot
samples <- data %>%
  group_by(music_genre) %>%
  slice_sample(n=20) %>%
  select(instrumentalness, acousticness,danceability,energy,popularity, loudness)
#plots of each 2 variables from rrfImp
options(repr.plot.height = 20, repr.plot.width = 20)
variables_plot <- ggpairs(samples, columns = 2:7, ggplot2::aes(colour = music_genre)) +
  ggtitle("plots of the most important variables in dataset") +
  theme(text = element_text(size =10))
variables_plot






# data_split <- initial_split(data, prop = 0.5, strata = music_genre)
# data_training <- training(data_split)
# data_testing <- testing(data_split)

# knn_spec <- nearest_neighbor(weight_func = "rectangular", 
#                              neighbors = tune()) %>%
#   set_engine("kknn")%>%
#   set_mode("classification")
# 
# data_vfold <- vfold_cv(data_training, v=5, strata = music_genre)
# 
# data_recipe <- recipe(music_genre ~ popularity+acousticness + danceability + duration_ms+
#                         energy + instrumentalness + liveness + loudness +
#                         speechiness +tempo +valence,
#                       data = data_training) %>%
#   step_scale(all_predictors()) %>%
#   step_center(all_predictors())
# 
# knn_spec <- nearest_neighbor(weight_func = "rectangular", neighbors = tune()) %>%
#   set_engine("kknn") %>%
#   set_mode("classification")
# 
# k_vals <- tibble(neighbors = seq(from = 1, to = 100, ))
# 
# knn_results <- workflow() %>%
#   add_recipe(data_recipe) %>%
#   add_model(knn_spec) %>%
#   tune_grid(resample = data_vfold, grid = 10) %>%
#   collect_metrics()
# 
# knn_results <- filter(knn_results, .metric == "accuracy")
# best_k_plot <- ggplot(knn_results, aes(x= neighbors, y = mean)) + geom_point()
# best_k_plot
# 
# knn_spec <- nearest_neighbor(weight_func = "rectangular", neighbors = 10) %>%
#   set_engine("kknn") %>%
#   set_mode("classification")
# 
# knn_fit <- workflow() %>%
#   add_recipe(data_recipe) %>%
#   add_model(knn_spec) %>%
#   fit(data = data_training)
# 
# data_test_predictions <- predict(knn_fit, data_testing) %>%
#   bind_cols(data_testing)
# data_test_predictions <- data_test_predictions %>%
#   metrics(truth = music_genre, estimate = .pred_class) %>%
#   filter(.metric == "accuracy")
#   