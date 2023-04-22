# OOKA - Java Runtime Environment


## Task answers


## Class diagram




## Open questions



## Resources



## LOGS

- Created maven projects for `RuntimeEnvironment` & `MyComponent`. [Source](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
- Added `ComponentAssembler` & `RuntimeEnv` classes.
  - [ ] Not sure how the Runtime class and Component Assembler should work together yet.
- Added Class Annotation `@Start` to Component. [Source](https://www.baeldung.com/java-custom-annotation)
  - [ ] Cant load retrieve annotation. 
    
    Currently importing the annotation from the MyComponent package itself. But in the main-Class of CompAssembler i cant retrieve any annotations of type @Start.

    [This stackoverflow post](https://stackoverflow.com/questions/29510159/class-getannotation-and-getannotations-doesnt-work-properly) might help

    > RetentionPolicy might not work properly. getAnnotations() seems to work, but getAnnotation(Class) doesnt.
- Command line tool [Source](https://www.tutorialspoint.com/commons_cli/commons_cli_quick_guide.htm)
- AppAssembler - Integration of dependencies into the same .jar file. [Source](http://www.mojohaus.org/appassembler/appassembler-maven-plugin/usage-program.html)
  - The file is only executable as a `bash` / `sh` file.