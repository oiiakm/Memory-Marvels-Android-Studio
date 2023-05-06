import openai 
from api_secrets import API_KEY

openai.api_key=API_KEY

prompt="Hi"
response=openai.Completion.create(engine="text-devinci-001",prompt=prompt,max_tokens=6)
print(response)