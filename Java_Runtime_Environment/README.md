# Runtime Environment

## Documentation

Documentation inside:
- [Docs (Task 2)](documentation/docs_task2.md)
- [Docs (Task 3)](documentation/docs_task3.md)

## Setup and Running

```bash
# Inside ./Runtime_Env install project into local maven repositories.
mvn install appassembler:assemble

# Inside ./MyComponent package project (Project .jar used as example component)
mvn package

# Back in ./Runtime_Env execute Main-Class `RuntimeEnviroment` directly
java -cp target/Java_Runtime_Env-1.0-SNAPSHOT.jar org.ooka.lzu.RuntimeEnvironment 

# *Alternatively* use generated executable CLI tool
bash target/appassembler/bin/CLI -a lzu -b start
```
