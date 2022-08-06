import os
import time
import joblib
import numpy as np
import librosa

#MFCC features in from librosa
feats = []

for sDirs, dirs, files in os.walk(trainingFiles):
    for file in files:
        try:
            feat, sampleRate = librosa.load(os.path.join(sDirs,file), res_type = 'keiser_fast')
            mfccs = np.mean( librosa.feature.mfcc (y=feat, sR = sampleRate, n_mfcc = 35).T ,
                             axis = 0)
            fileClass = int(file[7:8])- 1
            arra = mfcc, file_class
            feats.append(arra)

        except ValueError as err:
            print(err)
            continue

#saving into joblib
x, y = zip(*feats)
x, y = np.asarray(x), np.asarray(y)
print(x.shape, y.shape)

if not os.path.isdir(saveDir):
    os.makedirs(saveDir)
joblib.dump(x, os.path.join(saveDir, 'x.joblib'))
joblib.dump(y, os.path.join(saveDir, 'y.joblib'))

#making Conv1D of CNN
import tensorflow as tf
from tensorflow.keras.layers import Sequential, Dropout, Flatten, Activation, Dense, Conv1D
from sklearn.metrics import confusion_matrix, classification_report
from sklearn.model_selection import train_test_split

model = Sequential()
model.add(Conv1D(64, 5, input_shape=(35,1), padding = 'same'))
model.add(Activation('relu'))
model.add(Dropout(0.2))
model.add(Dense(8))
model.add(Flatten())
model.add(Activation('softmax'))

#training and testing
x_train, x_test, y_train, y_test = train_test_split (x, y, test_size = 0.35, random_state = 42)

x_testCNN = np.expand_dims(x_test, axis = 2)
x_trainCNN = np.expand_dims(x_train, axis = 2)

#plotting the accuracy
plt.plot(cnn_history.history ['accuracy'])
plt.plot(cnn_history.history ['val_accuracy'])

plt.title('model accuracy')
plt.xlabel('stage')
plt.ylabel('% of accuracy')

plt.legend(['train', 'test'], loc='lower right')
plt.close()

plt.plot(cnn_history.history ['loss'])
plt.plot(cnn_history.history ['val_aloss'])

plt.title('model loss')
plt.xlabel('stage')
plt.ylabel('% of loss')

plt.legend(['train', 'test'], loc='lower right')

#building CNN-LSTM
import matplotlib.pyplot as plt
from IPython.display import Image
from tensorflow.keras.layers import LSTM, Flatten, BatchNormalization, AveragePooling2D, Input,
                                    Conv2D, MaxPooling2D, Dense, Dropout, TimeDistribution, Activation
from tensorflow.keras.utils import plot_model, to_categorical
from tensorflow.keras import backend as K
from tensorflow.keras.optimizers import Adam, SDG
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint
from tensorflow.keras.models import Sequential, Model
from sklearn.preprocessing import LabelEncoder

input_y = Input(shape = x_train.shape[1:], name = 'Input')

y = TimeDistributed (Conv2D (64, kernel_size=(3,3), strides=(1,1), padding='same'), name='Conv1') (input_y)
y = TimeDistributed (BatchNormalization(), name = 'BatchNorm1') (y)
y = TimeDistributed (Activation('elu'), name = 'Activ1') (y)
y = TimeDistributed (MaxPooling2D (pool_size = (2,2), strides = (2,2)), padding = 'same', name = 'Maxpool1') (y)
y = TimeDistributed (Dropout(0.2), name = 'Drop1') (y)

y = TimeDistributed (Conv2D (64, kernel_size=(3,3), strides=(1,1), padding='same'), name='Conv2') (y)
y = TimeDistributed (BatchNormalization(), name = 'BatchNorm2') (y)
y = TimeDistributed (Activation('elu'), name = 'Activ2') (y)
y = TimeDistributed (MaxPooling2D (pool_size = (2,2), strides = (2,2)), padding = 'same', name = 'Maxpool2') (y)
y = TimeDistributed (Dropout(0.2), name = 'Drop2') (y)

y = TimeDistributed (Conv2D (64, kernel_size=(3,3), strides=(1,1), padding='same'), name='Conv3') (y)
y = TimeDistributed (BatchNormalization(), name = 'BatchNorm3') (y)
y = TimeDistributed (Activation('elu'), name = 'Activ3') (y)
y = TimeDistributed (MaxPooling2D (pool_size = (2,2), strides = (2,2)), padding = 'same', name = 'Maxpool3') (y)
y = TimeDistributed (Dropout(0.2), name = 'Drop3') (y)

y = TimeDistributed (Conv2D (64, kernel_size=(3,3), strides=(1,1), padding='same'), name='Conv4') (y)
y = TimeDistributed (BatchNormalization(), name = 'BatchNorm4') (y)
y = TimeDistributed (Activation('elu'), name = 'Activ4') (y)
y = TimeDistributed (MaxPooling2D (pool_size = (2,2), strides = (2,2)), padding = 'same', name = 'Maxpool4') (y)
y = TimeDistributed (Dropout(0.2), name = 'Drop4') (y)


y = TimeDistributed (Flatten(), name = 'Flat') (y)

y = LSTM(256, return_sequence= False, dropout = 0.2, name = 'LSTM1') (y)
y = Dense(y_train.shape[1], activation = 'softmax', name = 'FC')

model = Model(inputs=input_y, outputs = y)

#making spectograms
def spocto (y, sr = 15000, n_fft = 512, win_length = 256, hop_length = 128, window = 'hamming', n_mels = 128, fmax = 4100):
    feat = np.abs(librosa.stft (y, n_fft=n_fft, window = window, win_length = win_length,
                                hop_length = hop_length)) **2
    feat = librosa.feature.melspectograms (s = feat, sr = sr, n_mels = n_mels, fmax = fmax)
    feat = librosa.power_to_db (feat, ref = np.max)

    return feat

spects = np.asarray(list(map(specto, signal)))

#splitting
mel_spec_train, mel_spec_test, label_train, label_test = train_test_split(spects, labels, test_size = 0.3)
x_train = mel_spec_train
y_train = label_train
x_test = mel_spec_test
y_test = label_test

#time distributed for LSTM
window_size , gap = 128, 64

def fr(x, step_size, window):
    n_frames = 1+ int((x.shape[2] - window) / step_size)
    frames = np.zeros((x.shape[0], n_frames, x.shape[1], window)).astypr(np.float32)
    for tim in range(n_frames):
        frames[:,tim,:,:] = np.copy(x[:, :, (tim*step_size):(tim*step_size + window)]).astype(np.float32)

    return frames

x_train = fr(x_train, gap, window_size)
x_test = fr(x_test, gap, window_size)

#final result
model.compile(optimizer = SDG (lr = 0.01, decay = 10**-6, momentum = 0.8), loss = 'categorical_crossentropy',
              metrics = ['accuracy'])

summ = model.fit(x_train, y_train, batch_size = 64, epochs = 100, validation_data = (x_test, y_test))


