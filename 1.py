import os
import openai
openai.organization = "org-XZvPeKLMABf5ztWblZVUrEaS"
openai.api_key = os.getenv("OPENAI_API_KEY")
openai.Model.list()