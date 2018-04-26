# Lombok
Lombok is a java library that generates getter, setters, constructors etc with the help of annotations.

| Annotation | Description |
| --- | --- |
| @Getter | Creates getter methods for all properties |
| @Setter | Creates setter methods for all properties |
| @ToString | Generates String of classname, and each field separated by commas |
| @EqualsAndHashCode | Generate equals and hashcode methods. Has optional parameter to exclude fields |
| @RequiredArgsContructor | Generate constructors for all fields that are marked final or @NonNull |
| @Data |  Includes all of the above annotations |
| @NoArgsConstructor | Generate no argument constructor |
| @NonNull | Set on parameter of method or constructor and a NullPointerException will be thrown if parameter is null |
| @Slf4j | Creates Slf4j logger |