# tmb-currency

## Technology and Dependencie  
- Kotlin
- Coroutines
- Model-View-ViewModel (MVVM)
- Koin (DI)
- DataBinding
- Glide

## Architecture

![Architecture Flow Diagram](art/arch_flow.png)

The Application is split into a three layer architecture:
- Presentation
- Domain
- Data

#### Presentation

```app``` contains the UI files and handles binding of DI components from other modules.

#### Domain

The ```domain``` module contains domain model classes which represent the
data we will be handling across presentation and data layer.

Use cases are also provided in the domain layer and orchestrate the flow 
of data from the data layer onto the presentation layer and a split into
modular pieces serving one particular purpose.

#### Data

```data-remote``` Handles data interacting with the network and is later serverd up to the presentation layer through 
domain object


## Testing

Each module has its own tests except for the ```domain``` module which is catered for since its
part of the behavior under test.

In the ``data-remote`` module the responses are mocked using the mockwebserver and verified that they
are what we expect.

In the ```app``` module there are unit tests for the viewmodels and util classes 
and connected tests for the UI Screens.
