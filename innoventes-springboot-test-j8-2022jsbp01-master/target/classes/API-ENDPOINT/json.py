import random
import string

def generate_random_string(length):
    return ''.join(random.choice(string.ascii_letters) for _ in range(length))

def generate_random_email():
    return f"{generate_random_string(5)}@{generate_random_string(5)}.com"

def generate_random_url():
    return f"https://www.{generate_random_string(10)}.com"

def generate_random_company_code():
    return f"{generate_random_string(2)}{random.randint(10, 99)}{random.choice(['E', 'N'])}"

def generate_random_company_data():
    data = {
        "id": random.randint(1, 100),
        "companyName": generate_random_string(random.randint(1, 5)),
        "email": generate_random_email(),
        "strength": random.randint(0, 1000),
        "webSiteURL": generate_random_url(),
        "companyCode": generate_random_company_code()
    }
    return data

# Generate 10 random JSON objects
num_objects = 1000
for _ in range(num_objects):
    company_data = generate_random_company_data()
    print(company_data)
