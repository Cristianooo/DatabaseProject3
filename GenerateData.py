from faker import Faker
import pandas as pd
import random as rand
import sys
faker = Faker()

DataArray = []
TvModels = []


for x in range(0, int(sys.argv[2])):
    firstName = (faker.first_name())
    lastName = (faker.last_name())
    streetAddress = (faker.address())

    TvModel = rand.randint(1000, 9999)
    while TvModel not in TvModels:
        TvModel = rand.randint(1000, 9999)
        TvModels.append(TvModel)

    TvSize = rand.randint(32, 90)

    newsLetterBool = bool(rand.getrandbits(1))

    companyName = (faker.company())

    DataArray.insert(x, [firstName, lastName, streetAddress, TvModel, TvSize, newsLetterBool, companyName])

    my_df = pd.DataFrame(DataArray)
    my_df.to_csv(sys.argv[1] + '.csv', index=False, header=False)
